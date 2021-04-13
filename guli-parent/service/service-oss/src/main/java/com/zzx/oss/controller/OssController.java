package com.zzx.oss.controller;

import com.zzx.oss.service.OssService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzx
 * @date 2021-04-13 11:46:55
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public ResultTO uploadFileAvatar(MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return ResultTO.buildSuccess(url);
    }
}
