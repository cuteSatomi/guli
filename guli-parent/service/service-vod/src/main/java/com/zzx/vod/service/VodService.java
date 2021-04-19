package com.zzx.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzx
 * @date 2021-04-19 10:50:50
 */
public interface VodService {
    /**
     * 上传小节视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据id删除视频
     * @param videoId
     */
    void RemoveVideoById(String videoId) throws ClientException;
}
