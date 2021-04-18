package com.zzx.edu.mapper;

import com.zzx.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzx.edu.entity.vo.CoursePublishVO;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 在发布页查询课程信息
     * @param courseId
     * @return
     */
    public CoursePublishVO getPublishCourseInfo(String courseId);
}
