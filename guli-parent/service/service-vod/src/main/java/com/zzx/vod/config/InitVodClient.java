package com.zzx.vod.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author zzx
 * @date 2021-04-19 14:49:24
 */
public class InitVodClient {

    public static DefaultAcsClient init(String accessKeyId, String accessSecretId) {
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecretId);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
