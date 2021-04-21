package com.zzx.edu.controller.front;

import com.zzx.edu.entity.Course;
import com.zzx.edu.entity.Teacher;
import com.zzx.edu.service.CourseService;
import com.zzx.edu.service.TeacherService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-21 11:29:53
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/front")
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询前8门热门课程，前8位讲师
     *
     * @return
     */
    @GetMapping("/index")
    public ResultTO index() {
        List<Course> courses = courseService.query8Courses();
        List<Teacher> teachers = teacherService.query4Teachers();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courses", courses);
        map.put("teachers", teachers);
        return ResultTO.buildSuccess(map);
    }
}
