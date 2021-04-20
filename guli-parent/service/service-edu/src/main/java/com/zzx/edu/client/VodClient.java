package com.zzx.edu.client;

import com.zzx.utils.ResultTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zzx
 * @date 2021-04-19 21:01
 */
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    /**
     * 根据videoId删除阿里云视频
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("/edu/vod/removeVideo/{videoId}")
    ResultTO removeVideoById(@PathVariable("videoId") String videoId);

    /**
     * 根据视频id字符串批量删除阿里云视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/edu/vod/deleteBatch")
    ResultTO deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
