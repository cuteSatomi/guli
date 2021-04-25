package com.zzx.order.service.impl;

import com.zzx.order.entity.PayLog;
import com.zzx.order.mapper.PayLogMapper;
import com.zzx.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
