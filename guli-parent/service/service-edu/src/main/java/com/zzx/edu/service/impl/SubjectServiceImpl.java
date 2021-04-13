package com.zzx.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.edu.entity.Subject;
import com.zzx.edu.entity.excel.SubjectData;
import com.zzx.edu.listener.SubjectExcelListener;
import com.zzx.edu.mapper.SubjectMapper;
import com.zzx.edu.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-13
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void addSubject(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
