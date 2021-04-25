package com.zzx.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zzx.base.exception.GuliException;
import com.zzx.utils.ResultCode;
import com.zzx.utils.ResultTO;
import com.zzx.vod.config.InitVodClient;
import com.zzx.vod.config.VodConfig;
import com.zzx.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 根据videoId删除阿里云视频
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("/removeVideo/{videoId}")
    public ResultTO removeVideoById(@PathVariable String videoId) {
        try {
            vodService.removeVideoById(videoId);
            return ResultTO.buildSuccess("删除视频成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "删除视频失败");
        }
    }

    /**
     * 根据视频id字符串批量删除阿里云视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/deleteBatch")
    public ResultTO deleteBatch(@RequestParam("videoIdList") List videoIdList) {
        try {
            vodService.deleteBatchVideo(videoIdList);
            return ResultTO.buildSuccess("批量删除视频成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "批量删除视频失败");
        }
    }

    /**
     * 根据视频id获取视频凭证
     *
     * @param videoId
     * @return
     */
    @GetMapping("/getPlayAuth/{videoId}")
    public ResultTO getPlayAuth(@PathVariable String videoId) {
        try {
            DefaultAcsClient client = InitVodClient.init(VodConfig.KEY_ID, VodConfig.KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            // 设置视频id
            request.setVideoId(videoId);
            // client根据request请求得到response
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return ResultTO.buildSuccess(playAuth);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR,"请求视频凭证失败");
        }
    }
}
