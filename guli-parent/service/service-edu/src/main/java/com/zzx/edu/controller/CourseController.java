package com.zzx.edu.controller;


import com.zzx.edu.entity.vo.CourseVO;
import com.zzx.edu.service.CourseService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

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
}

