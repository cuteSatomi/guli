package com.zzx.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzx.edu.entity.vo.TeacherQuery;
import com.zzx.edu.entity.Teacher;
import com.zzx.edu.service.TeacherService;
import com.zzx.utils.ResultTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-10
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有老师
     *
     * @return
     */
    @GetMapping("/findAll")
    public ResultTO findAllTeacher() {
        List<Teacher> teacherList = teacherService.list(null);
        return ResultTO.buildSuccess(teacherList);
    }

    /**
     * 根据id逻辑删除老师
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultTO deleteTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag ? ResultTO.buildSuccess(null) : ResultTO.buildFailed(500, "删除失败");
    }

    /**
     * 分页查询老师列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/pageTeacher/{current}/{size}")
    public ResultTO pageTeacher(@PathVariable Integer current, @PathVariable Integer size) {
        Page<Teacher> teacherPage = new Page<>(current, size);
        teacherService.page(teacherPage, null);

        return ResultTO.buildSuccess(teacherPage);
    }

    /**
     * 带复杂条件的分页查询
     *
     * @param current
     * @param size
     * @param teacherQuery
     * @return
     */
    @PostMapping("/pageTeacherCondition/{current}/{size}")
    public ResultTO pageTeacherCondition(@PathVariable Integer current,
                                         @PathVariable Integer size,
                                         @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> teacherPage = new Page<>(current, size);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (StringUtils.isNoneBlank(name)) {
            wrapper.like("name", name);
        }
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (StringUtils.isNoneBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (StringUtils.isNoneBlank(end)) {
            wrapper.le("gmt_create", end);
        }

        teacherService.page(teacherPage, wrapper);
        return ResultTO.buildSuccess(teacherPage);
    }

    /**
     * 新增老师
     *
     * @param teacher
     * @return
     */
    @PostMapping("/addTeacher")
    public ResultTO addTeacher(@RequestBody Teacher teacher) {
        boolean flag = teacherService.save(teacher);
        return flag ? ResultTO.buildSuccess(true) : ResultTO.buildFailed(500, "插入失败");
    }

    /**
     * 根据id查询老师
     *
     * @param id
     * @return
     */
    @GetMapping("/getTeacherById/{id}")
    public ResultTO getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return ResultTO.buildSuccess(teacher);
    }

    /**
     * 更新老师
     *
     * @param teacher
     * @return
     */
    @PostMapping("/updateTeacher")
    public ResultTO updateTeacher(@RequestBody Teacher teacher) {
        boolean flag = teacherService.updateById(teacher);
        return flag ? ResultTO.buildSuccess(true) : ResultTO.buildFailed(500, "更新失败");
    }
}

