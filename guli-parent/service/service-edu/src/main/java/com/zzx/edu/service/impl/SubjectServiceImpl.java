package com.zzx.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.edu.entity.Subject;
import com.zzx.edu.entity.excel.SubjectData;
import com.zzx.edu.entity.vo.SubjectVO;
import com.zzx.edu.listener.SubjectExcelListener;
import com.zzx.edu.mapper.SubjectMapper;
import com.zzx.edu.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-13
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void addSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectVO> getAllSubjects() {
        List<Subject> list = this.list(null);
        List<SubjectVO> result = new ArrayList<SubjectVO>();
        // 存放二级分类
        Map<String, List<SubjectVO>> map = new HashMap<String, List<SubjectVO>>();
        for (Subject subject : list) {
            if ("0".equals(subject.getParentId())) {
                // 一级分类直接存入result中
                SubjectVO subjectVO = new SubjectVO();
                subjectVO.setId(subject.getId());
                subjectVO.setTitle(subject.getTitle());
                result.add(subjectVO);
                continue;
            }
            // 如果是二级分类，且map中不存在parent_id为key的键值对
            List<SubjectVO> l2Subject;
            // l2Subject这个list分两种情况，当前map不存在时new一个，当前map已经存在则通过get获取
            if (!map.containsKey(subject.getParentId())) {
                l2Subject = new ArrayList<>();
            } else {
                l2Subject = map.get(subject.getParentId());
            }
            SubjectVO l2SubjectVO = new SubjectVO();
            l2SubjectVO.setId(subject.getId());
            l2SubjectVO.setTitle(subject.getTitle());
            l2Subject.add(l2SubjectVO);
            map.put(subject.getParentId(), l2Subject);
        }

        // 再根据一级分类的id进行匹配
        /*
            由于一开始result中只存有一级分类，这时候对result进行遍历得到的课程分类的id其实就是二级分类的parent_id，
            而map的键就是二级分类的parent_id。
         */
        for (SubjectVO subjectVO : result) {
            List<SubjectVO> l2List = map.get(subjectVO.getId());
            subjectVO.setChildren(l2List);
        }

        return result;
    }
}
