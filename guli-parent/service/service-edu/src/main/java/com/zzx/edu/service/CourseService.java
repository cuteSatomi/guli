package com.zzx.edu.service;

import com.zzx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.edu.entity.vo.CourseVO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
public interface CourseService extends IService<Course> {

    /**
     * 添加课程信息
     *
     * @param courseVO
     */
    String addCourseInfo(CourseVO courseVO);

    /**
     * 根据课程id查询课程信息，包括description描述信息
     *
     * @param courseId
     * @return
     */
    CourseVO getCourseInfo(String courseId);

    /**
     * 修改课程信息
     *
     * @param courseVO
     */
    void updateCourseInfo(CourseVO courseVO);
}
