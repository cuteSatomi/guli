package com.zzx.edu.service;

import com.zzx.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
