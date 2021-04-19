package com.zzx.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    void removeVideoById(String videoId) throws ClientException;

    /**
     * 根据视频id字符串批量删除阿里云视频
     * @param videoIdList
     * @throws ClientException
     */
    void deleteBatchVideo(List videoIdList) throws ClientException;
}
