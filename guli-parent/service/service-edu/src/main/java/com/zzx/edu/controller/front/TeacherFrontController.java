package com.zzx.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzx.edu.entity.Course;
import com.zzx.edu.entity.Teacher;
import com.zzx.edu.service.CourseService;
import com.zzx.edu.service.TeacherService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-24 13:06
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/front/teacher")
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    /**
     * 前台页面分页查询讲师列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/getFrontTeacherList/{current}/{size}")
    public ResultTO getFrontTeacherList(@PathVariable long current, @PathVariable long size) {
        Page<Teacher> teacherPage = new Page<>(current, size);
        Map<String, Object> map = teacherService.getFrontTeacherList(teacherPage);
        return ResultTO.buildSuccess(map);
    }

    @GetMapping("/getFrontTeacherInfo/{id}")
    public ResultTO getFrontTeacherInfo(@PathVariable String id) {
        // 根据讲师id查询讲师信息
        Teacher teacher = teacherService.getById(id);

        // 根据讲师id查询所讲课程
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();
        queryWrapper.eq("teacher_id", id);
        List<Course> courseList = courseService.list(queryWrapper);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teacher", teacher);
        map.put("courseList", courseList);

        return ResultTO.buildSuccess(map);
    }
}
