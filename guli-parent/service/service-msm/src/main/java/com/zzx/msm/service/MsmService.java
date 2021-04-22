package com.zzx.msm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.msm.entity.Member;

import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-21 16:47:33
 */
public interface MsmService extends IService<Member> {
    boolean sendMsm(String phone, Map<String,Object> param);
}
