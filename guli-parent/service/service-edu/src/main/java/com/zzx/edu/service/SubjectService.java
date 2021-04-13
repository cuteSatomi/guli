package com.zzx.edu.service;

import com.zzx.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-13
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 读取excel文件
     * @param file
     */
    void addSubject(MultipartFile file);
}
