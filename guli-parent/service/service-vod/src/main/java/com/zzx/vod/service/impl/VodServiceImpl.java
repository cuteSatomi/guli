package com.zzx.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zzx.vod.config.InitVodClient;
import com.zzx.vod.config.VodConfig;
import com.zzx.vod.service.VodService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zzx
 * @date 2021-04-19 10:51:01
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VodConfig.KEY_ID,
                    VodConfig.KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = "";
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = "";
            }

            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeVideoById(String videoId) throws ClientException {
        DefaultAcsClient client = InitVodClient.init(VodConfig.KEY_ID, VodConfig.KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }

    @Override
    public void deleteBatchVideo(List videoIdList) throws ClientException {
        DefaultAcsClient client = InitVodClient.init(VodConfig.KEY_ID, VodConfig.KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        String videoIds = StringUtils.join(videoIdList.toArray(), ",");
        request.setVideoIds(videoIds);
        client.getAcsResponse(request);
    }
}
