package com.zzx.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zzx.oss.config.OssConfig;
import com.zzx.oss.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zzx
 * @date 2021-04-13 11:48:59
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endPoint = OssConfig.END_POINT;
        String keyId = OssConfig.KEY_ID;
        String keySecret = OssConfig.KEY_SECRET;
        String bucketName = OssConfig.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endPoint, keyId, keySecret);
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid + filename;

            String datePath = new DateTime().toString("yyyy-MM-dd");
            filename = datePath + "/" + filename;

            ossClient.putObject(bucketName, filename, inputStream);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." + endPoint + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
