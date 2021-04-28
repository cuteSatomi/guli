package com.zzx.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzx.edu.entity.Course;
import com.zzx.edu.entity.vo.CoursePublishVO;
import com.zzx.edu.entity.vo.CourseQuery;
import com.zzx.edu.entity.vo.CourseVO;
import com.zzx.edu.service.CourseService;
import com.zzx.utils.ResultTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/edu/service/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 查询所有的课程
     *
     * @return
     */
    @GetMapping("/findAll")
    public ResultTO findAllCourse() {
        List<Course> courseList = courseService.list(null);
        return ResultTO.buildSuccess(courseList);
    }

    /**
     * 带复杂条件的分页查询
     *
     * @param current
     * @param size
     * @param courseQuery
     * @return
     */
    @PostMapping("/pageCourseCondition/{current}/{size}")
    public ResultTO pageTeacherCondition(@PathVariable Integer current,
                                         @PathVariable Integer size,
                                         @RequestBody(required = false) CourseQuery courseQuery) {
        Page<Course> coursePage = new Page<Course>(current, size);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        if (StringUtils.isNoneBlank(title)) {
            wrapper.like("title", title);
        }
        if (status != null) {
            wrapper.eq("status", status);
        }
        if (StringUtils.isNoneBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (StringUtils.isNoneBlank(end)) {
            wrapper.le("gmt_create", end);
        }

        courseService.page(coursePage, wrapper);
        return ResultTO.buildSuccess(coursePage);
    }

    @PostMapping("/addCourseInfo")
    public ResultTO addCourseInfo(@RequestBody CourseVO courseVO) {
        String id = courseService.addCourseInfo(courseVO);
        return ResultTO.buildSuccess(id);
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public ResultTO getCourseInfo(@PathVariable String courseId) {
        CourseVO courseVO = courseService.getCourseInfo(courseId);
        return ResultTO.buildSuccess(courseVO);
    }

    @PostMapping("/updateCourseInfo")
    public ResultTO updateCourseInfo(@RequestBody CourseVO courseVO) {
        courseService.updateCourseInfo(courseVO);
        return ResultTO.buildSuccess("修改成功");
    }

    @GetMapping("/getPublishCourseInfo/{courseId}")
    public ResultTO getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVO publishCourseInfo = courseService.getPublishCourseInfo(courseId);
        return ResultTO.buildSuccess(publishCourseInfo);
    }

    @PostMapping("/publishCourse/{courseId}")
    public ResultTO publishCourse(@PathVariable String courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return ResultTO.buildSuccess("课程发布成功");
    }
}

