package com.zzx.edu.service.impl;

import com.zzx.base.exception.GuliException;
import com.zzx.edu.entity.Course;
import com.zzx.edu.entity.CourseDescription;
import com.zzx.edu.entity.vo.CourseVO;
import com.zzx.edu.mapper.CourseDescriptionMapper;
import com.zzx.edu.mapper.CourseMapper;
import com.zzx.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Override
    @Transactional(rollbackFor = GuliException.class)
    public void addCourseInfo(CourseVO courseVO) {
        // 获取course的部分数据插入到course表中
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        baseMapper.insert(course);

        String cid = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseVO.getDescription());
        courseDescription.setId(cid);
        courseDescriptionMapper.insert(courseDescription);
    }
}
