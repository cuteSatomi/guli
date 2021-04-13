package com.zzx.oss.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zzx
 * @date 2021-04-13 11:11:25
 */
@Component
public class OssConfig implements InitializingBean {
    @Value("oss-cn-shanghai.aliyuncs.com")
    private String endpoint;
    @Value("LTAI5tNHhp1bjq2K6AmGHTbn")
    private String keyId;
    @Value("n3U8ADwTqi2NftsESZMd77vjwVMKwU")
    private String keySecret;
    @Value("edu-0412")
    private String bucketName;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
