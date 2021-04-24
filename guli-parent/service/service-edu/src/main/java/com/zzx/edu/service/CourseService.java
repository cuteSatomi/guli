package com.zzx.edu.service;

import com.zzx.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.edu.entity.frontvo.FrontCourseDetailsVO;
import com.zzx.edu.entity.frontvo.FrontCourseVO;
import com.zzx.edu.entity.vo.CoursePublishVO;
import com.zzx.edu.entity.vo.CourseVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 在发布页查询的课程信息
     *
     * @param courseId
     * @return
     */
    CoursePublishVO getPublishCourseInfo(String courseId);

    /**
     * 查询前8个热门课程
     *
     * @return
     */
    List<Course> query8Courses();

    /**
     * 查询前台课程列表
     *
     * @param current
     * @param size
     * @param frontCourseVO
     * @return
     */
    Map<String, Object> getFrontCourseList(long current, long size, FrontCourseVO frontCourseVO);

    /**
     * 根据课程id查出相关联的信息
     *
     * @param courseId
     * @return
     */
    FrontCourseDetailsVO getBaseCourseInfo(String courseId);
}
