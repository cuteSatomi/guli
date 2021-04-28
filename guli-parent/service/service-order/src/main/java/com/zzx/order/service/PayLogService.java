package com.zzx.order.service;

import com.zzx.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 根据订单号生成微信支付二维码
     *
     * @param orderNo
     * @return
     */
    Map<String, Object> createQR(String orderNo);

    /**
     * 根据订单号查询订单状态
     *
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 添加记录到支付表，更新订单状态
     *
     * @param map
     */
    void updateOrderState(Map<String, String> map);
}
