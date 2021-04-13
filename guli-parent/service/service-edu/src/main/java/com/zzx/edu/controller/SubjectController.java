package com.zzx.edu.controller;


import com.zzx.edu.service.SubjectService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-13
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addSubject")
    public ResultTO addSubject(MultipartFile file){
        subjectService.addSubject(file);
        return ResultTO.buildSuccess("");
    }
}

