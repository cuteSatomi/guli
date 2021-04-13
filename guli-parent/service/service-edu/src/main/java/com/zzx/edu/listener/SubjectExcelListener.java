package com.zzx.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.base.exception.GuliException;
import com.zzx.edu.entity.Subject;
import com.zzx.edu.entity.excel.SubjectData;
import com.zzx.edu.service.SubjectService;
import com.zzx.utils.ResultCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-13 20:24
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /** 用来接收后的excel数据 */
    private Map<String, List<String>> map = new HashMap<String, List<String>>();
    /** 用来存储数据库中的二级分类 */
    private List<String> levelTwoSubjects = new ArrayList<String>();

    private SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;

        // 每次new当前对象的时候，都会去请求数据库中的二级分类，即查询出所有parent_id不等于0的分类
        QueryWrapper<Subject> wrapper = new QueryWrapper<Subject>();
        wrapper.ne("parent_id", "0");
        List<Subject> list = subjectService.list(wrapper);
        for (Subject subject : list) {
            this.levelTwoSubjects.add(subject.getTitle());
        }
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (data == null) {
            throw new GuliException(ResultCode.ERROR, "文件为空");
        }
        List<String> list;
        if (!map.containsKey(data.getOneSubjectName())) {
            // 如果当前map不存在以当前一级分类为key的entry，则new一个list
            list = new ArrayList<>();
        } else {
            // 已经存在，则取出
            list = map.get(data.getOneSubjectName());
        }
        // 如果当前二级分类已存在，则不允许再次添加(无论一级分类是否和库中的一致)
        if (!levelTwoSubjects.contains(data.getTwoSubjectName())) {
            list.add(data.getTwoSubjectName());
        }
        map.put(data.getOneSubjectName(), list);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 当文件读取完毕，map中已经存放了需要插入的数据
        for (Map.Entry<String, List<String>> dataEntry : map.entrySet()) {
            String levelOneTitle = dataEntry.getKey();
            // 根据一级分类查询
            Subject subjectOne = isExistLevelOne(levelOneTitle);
            // 用于存放批量插入的二级分类
            List<Subject> subjectTwoList = new ArrayList<>();
            if (subjectOne == null) {
                // 如果当前要插入的一级分类不存在，则先插入一级分类，再根据一级分类插入二级分类
                subjectOne = new Subject();
                subjectOne.setParentId("0");
                subjectOne.setTitle(levelOneTitle);
                subjectService.save(subjectOne);

                // 根据默认主键策略，插入完成后subjectOne对象中应该有id值，根据这个id值去插入二级分类
                for (String levelTwoTitle : dataEntry.getValue()) {
                    Subject subjectTwo = new Subject();
                    subjectTwo.setTitle(levelTwoTitle);
                    subjectTwo.setParentId(subjectOne.getId());
                    subjectTwoList.add(subjectTwo);
                }
            }else {
                // 如果已存在，则直接根据查询得到的id当作parent_id来插入
                for (String levelTwoTitle : dataEntry.getValue()) {
                    Subject subjectTwo = new Subject();
                    subjectTwo.setTitle(levelTwoTitle);
                    subjectTwo.setParentId(subjectOne.getId());
                    subjectTwoList.add(subjectTwo);
                }
            }
            // 批量插入
            subjectService.saveBatch(subjectTwoList);
        }
    }

    /**
     * 判断当前一级分类是否已经存在
     *
     * @param title
     * @return
     */
    public Subject isExistLevelOne(String title) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<Subject>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }
}
