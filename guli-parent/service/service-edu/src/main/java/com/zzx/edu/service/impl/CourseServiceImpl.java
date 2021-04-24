package com.zzx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.base.exception.GuliException;
import com.zzx.edu.entity.Course;
import com.zzx.edu.entity.CourseDescription;
import com.zzx.edu.entity.frontvo.FrontCourseDetailsVO;
import com.zzx.edu.entity.frontvo.FrontCourseVO;
import com.zzx.edu.entity.vo.CoursePublishVO;
import com.zzx.edu.entity.vo.CourseVO;
import com.zzx.edu.mapper.CourseDescriptionMapper;
import com.zzx.edu.mapper.CourseMapper;
import com.zzx.edu.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void updateCourseInfo(CourseVO courseVO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        baseMapper.updateById(course);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseVO.getId());
        courseDescription.setDescription(courseVO.getDescription());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    @Transactional(rollbackFor = GuliException.class)
    public String addCourseInfo(CourseVO courseVO) {
        // 获取course的部分数据插入到course表中
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        baseMapper.insert(course);

        String cid = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseVO.getDescription());
        courseDescription.setId(cid);
        courseDescriptionMapper.insert(courseDescription);

        return cid;
    }

    @Override
    public CourseVO getCourseInfo(String courseId) {
        // 根据courseId查询课程信息
        Course course = baseMapper.selectById(courseId);
        CourseVO courseVO = new CourseVO();
        // 将course的属性复制到courseVO中
        BeanUtils.copyProperties(course, courseVO);

        CourseDescription courseDescription = courseDescriptionMapper.selectById(courseId);
        courseVO.setDescription(courseDescription.getDescription());

        return courseVO;
    }

    @Override
    public CoursePublishVO getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public List<Course> query8Courses() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getFrontCourseList(long current, long size, FrontCourseVO frontCourseVO) {
        Page<Course> coursePage = new Page<Course>(current, size);
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        // 判断VO中的参数
        if (StringUtils.isNotBlank(frontCourseVO.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", frontCourseVO.getSubjectParentId());
        }
        if (StringUtils.isNotBlank(frontCourseVO.getSubjectId())) {
            wrapper.eq("subject_id", frontCourseVO.getSubjectId());
        }
        if (StringUtils.isNotBlank(frontCourseVO.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        if (StringUtils.isNotBlank(frontCourseVO.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }
        if (StringUtils.isNotBlank(frontCourseVO.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(coursePage, wrapper);

        List<Course> records = coursePage.getRecords();
        current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        size = coursePage.getSize();
        long total = coursePage.getTotal();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();

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

    @Override
    public FrontCourseDetailsVO getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
