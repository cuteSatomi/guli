package com.zzx.edu.controller;


import com.zzx.edu.entity.Video;
import com.zzx.edu.service.VideoService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-14
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/service/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/addVideo")
    public ResultTO addVideo(@RequestBody Video video) {
        videoService.save(video);
        return ResultTO.buildSuccess("新增小节成功");
    }

    @GetMapping("/getVideo/{videoId}")
    public ResultTO getVideoById(@PathVariable String videoId) {
        Video video = videoService.getById(videoId);
        return ResultTO.buildSuccess(video);
    }

    @PostMapping("/updateVideo")
    public ResultTO updateVideo(@RequestBody Video video) {
        videoService.updateById(video);
        return ResultTO.buildSuccess("更新小节成功");
    }

    @DeleteMapping("/{videoId}")
    public ResultTO deleteVideoById(@PathVariable String videoId) {
        videoService.removeById(videoId);
        return ResultTO.buildSuccess("删除小节成功");
    }
}

