package com.zzx.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.zzx.base.exception.GuliException;
import com.zzx.order.entity.Order;
import com.zzx.order.entity.PayLog;
import com.zzx.order.mapper.PayLogMapper;
import com.zzx.order.service.OrderService;
import com.zzx.order.service.PayLogService;
import com.zzx.order.utils.HttpClient;
import com.zzx.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private OrderService orderService;

    @Override
    public Map<String, Object> createQR(String orderNo) {

        try {
            // 先根据订单号查询出订单信息
            QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
            wrapper.eq("order_no", orderNo);
            Order order = orderService.getOne(wrapper);

            Map<String, String> m = new HashMap<String, String>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle()); //课程标题
            m.put("out_trade_no", orderNo); //订单号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");

            // 发送请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            // 设置xml格式参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            // 执行请求发送
            client.post();

            // 得到返回结果，将xml格式的返回结果转为map
            String xml = client.getContent();
            Map<String,String> resultMap = WXPayUtil.xmlToMap(xml);

            //4、封装返回结果集
            Map<String,Object> map = new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));  //返回二维码操作状态码
            map.put("code_url", resultMap.get("code_url"));        //二维码地址

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "生成支付二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            // 封装参数
            Map<String, String> m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            // 设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            // 返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateOrderState(Map<String, String> map) {
        // 获取订单号
        String orderNo = map.get("out_trade_no");
        // 根据订单号查询出订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);
        if (order.getStatus() == 1) {
            return;
        }
        // 将order的state设置为已支付
        order.setStatus(1);
        // 更新订单
        orderService.updateById(order);

        // 向支付表添加支付记录
        PayLog payLog = new PayLog();

        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));

        baseMapper.insert(payLog);
    }
}
