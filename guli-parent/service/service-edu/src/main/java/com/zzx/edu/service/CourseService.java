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
     * @param courseVO
     */
    String addCourseInfo(CourseVO courseVO);
}
