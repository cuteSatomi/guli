package com.zzx.center.controller;

import com.google.gson.Gson;
import com.zzx.base.exception.GuliException;
import com.zzx.center.config.WxOpenConfig;
import com.zzx.center.entity.Member;
import com.zzx.center.service.MemberService;
import com.zzx.center.utils.HttpClientUtils;
import com.zzx.utils.JwtUtils;
import com.zzx.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author zzx
 * @date 2021-04-23 11:35:02
 */
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/callback")
    public String callback(String code, String state) {
        try {
            // 拿返回的code去请求固定的微信地址
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            String accessTokenUrl = String.format(baseAccessTokenUrl, WxOpenConfig.WX_OPEN_APP_ID,
                    WxOpenConfig.WX_OPEN_APP_SECRET, code);

            // 请求拼接好的地址，拿到access_token 和 openid
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            Gson gson = new Gson();
            HashMap map = gson.fromJson(accessTokenInfo, HashMap.class);
            String accessToken = (String) map.get("access_token");
            String openid = (String) map.get("openid");

            // 判断当前扫码人是否已经注册
            Member member = memberService.selectMemberByOpenid(openid);

            if (member == null) {
                // 拿着 accessToken 和 openid 去请求微信的资源服务器获取信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
                // 发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");

                // 不存在，把扫码人信息添加到数据库
                member = new Member();
                member.setAvatar(headimgurl);
                member.setNickname(nickname);
                member.setOpenid(openid);
                memberService.save(member);
            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            // 返回首页
            return "redirect:http://localhost:3000?token=" + jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "登录失败");
        }
    }

    @GetMapping("/login")
    public String getQrConnect() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 对redirectUrl进行encode编码
        String redirectUrl = WxOpenConfig.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = String.format(baseUrl, WxOpenConfig.WX_OPEN_APP_ID, redirectUrl, "atguigu");
        return "redirect:" + url;
    }
}
