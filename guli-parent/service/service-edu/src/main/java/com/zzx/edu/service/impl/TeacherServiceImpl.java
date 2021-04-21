package com.zzx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.edu.entity.Teacher;
import com.zzx.edu.mapper.TeacherMapper;
import com.zzx.edu.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
