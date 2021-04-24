package com.zzx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.edu.entity.Teacher;
import com.zzx.edu.mapper.TeacherMapper;
import com.zzx.edu.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-10
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public List<Teacher> query4Teachers() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>();
        queryWrapper.orderByDesc("level");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getFrontTeacherList(Page<Teacher> teacherPage) {
        baseMapper.selectPage(teacherPage, null);
        List<Teacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

        // 将数据存入map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("records", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
