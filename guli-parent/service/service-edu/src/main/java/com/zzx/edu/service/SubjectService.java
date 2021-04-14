package com.zzx.edu.service;

import com.zzx.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.edu.entity.vo.SubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 查询出所有的subject，并且按照一二级分类的结构进行封装
     * @return
     */
    List<SubjectVO> getAllSubjects();
}
