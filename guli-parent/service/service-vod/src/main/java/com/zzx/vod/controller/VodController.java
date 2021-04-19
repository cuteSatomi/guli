package com.zzx.vod.controller;

import com.zzx.base.exception.GuliException;
import com.zzx.utils.ResultCode;
import com.zzx.utils.ResultTO;
import com.zzx.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzx
 * @date 2021-04-19 10:50:08
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadVideo")
    public ResultTO uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return ResultTO.buildSuccess(videoId);
    }

    @DeleteMapping("/removeVideo/{videoId}")
    public ResultTO RemoveVideoById(@PathVariable String videoId) {
        try {
            vodService.RemoveVideoById(videoId);
            return ResultTO.buildSuccess("删除视频成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "删除视频失败");
        }
    }
}
