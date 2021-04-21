package com.zzx.msm.controller;

import com.zzx.msm.service.MsmService;
import com.zzx.msm.utils.RandomUtil;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zzx
 * @date 2021-04-21 16:42:00
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信
     *
     * @param phone
     * @return
     */
    @GetMapping("/send/{phone}")
    public ResultTO sendMsm(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return ResultTO.buildSuccess("");
        }
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        boolean isOk = msmService.sendMsm(phone, param);
        if (isOk) {
            // 发送成功，将验证码存入redis中，过期时间是五分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ResultTO.buildSuccess("短信发送成功");
        } else {
            return ResultTO.buildFailed("短信发送失败");
        }
    }
}
