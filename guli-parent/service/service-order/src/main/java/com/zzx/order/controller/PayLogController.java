package com.zzx.order.controller;


import com.zzx.order.service.PayLogService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/order/paylog")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * 根据订单号生成微信支付二维码
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/createQR/{orderNo}")
    public ResultTO createQR(@PathVariable String orderNo) {
        Map<String, Object> map = payLogService.createQR(orderNo);
        return ResultTO.buildSuccess(map);
    }

    /**
     * 根据订单号查询订单状态
     *
     * @param orderNo
     * @return
     */
    @GetMapping("/queryPayStatus/{orderNo}")
    public ResultTO queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return ResultTO.buildFailed("支付出错了");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            // 添加记录到支付表，更新订单状态
            payLogService.updateOrderState(map);
            return ResultTO.buildSuccess("支付成功");
        }
        return ResultTO.buildSuccess("支付中...").setCode(25000);
    }
}

