package com.zzx.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzx.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-10
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 查询前4个名师
     * @return
     */
    List<Teacher> query4Teachers();

    /**
     * 分页查询前台讲师列表
     * @param teacherPage
     * @return
     */
    Map<String, Object> getFrontTeacherList(Page<Teacher> teacherPage);
}
