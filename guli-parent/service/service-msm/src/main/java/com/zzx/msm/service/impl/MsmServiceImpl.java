package com.zzx.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.base.exception.GuliException;
import com.zzx.msm.config.MsmConfig;
import com.zzx.msm.entity.Member;
import com.zzx.msm.mapper.MemberMapper;
import com.zzx.msm.service.MsmService;
import com.zzx.utils.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-21 16:47:44
 */
@Service
public class MsmServiceImpl extends ServiceImpl<MemberMapper, Member> implements MsmService {
    @Override
    public boolean sendMsm(String phone, Map<String, Object> param) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("mobile", phone);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count >= 1) {
            throw new GuliException(ResultCode.ERROR, "该手机号码已被注册");
        }

        DefaultProfile profile =
                DefaultProfile.getProfile("default", MsmConfig.KEY_ID, MsmConfig.KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", MsmConfig.SIGN_NAME);
        request.putQueryParameter("TemplateCode", MsmConfig.TEMPLATE);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
