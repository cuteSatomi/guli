package com.zzx.center.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzx
 * @date 2021-04-23 11:22:04
 */
@Configuration
public class WxOpenConfig implements InitializingBean {

    @Value("${wx.open.appid}")
    private String appId;
    @Value("${wx.open.appsecret}")
    private String appSecret;
    @Value("${wx.open.redirecturl}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
