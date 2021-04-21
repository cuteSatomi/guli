package com.zzx.msm.service;

import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-21 16:47:33
 */
public interface MsmService {
    boolean sendMsm(String phone, Map<String,Object> param);
}
