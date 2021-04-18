package com.zzx.vod.test;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @author zzx
 * @date 2021-04-18 20:25
 */
public class VodTest {
    public static void main(String[] args) {
        UploadVideoRequest request = new UploadVideoRequest("",
                "", "ATTACK ON TITAN",
                "/Users/hds0m3zzx/Movies/6 - What If I Want to Move Faster.mp4");
        request.setPartSize(2 * 1024 * 1024L);
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("ResponseId = " + response.getRequestId() + "\n");
        if (response.isSuccess()) {
            System.out.print("VideoId = " + response.getVideoId() + "\n");
        } else {
            System.out.print("VideoId = " + response.getVideoId() + "\n");
            System.out.print("ErrorCode = " + response.getCode() + "\n");
            System.out.print("ErrorMessage = " + response.getMessage() + "\n");
        }

    }

    public static void getPlayURL() throws ClientException {
        DefaultAcsClient client = initVodClient("", "");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("f475bd9d66b346169d58f6faf7f316d8");
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
