package com.zzx.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.order.entity.Order;
import com.zzx.order.service.OrderService;
import com.zzx.utils.JwtUtils;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     * @param courseId
     * @param request
     * @return
     */
    @GetMapping("/createOrder/{courseId}")
    public ResultTO createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId, memberId);
        return ResultTO.buildSuccess(orderNo);
    }

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    @GetMapping("/getOrderInfo/{orderNo}")
    public ResultTO getOrderInfo(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<Order>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return ResultTO.buildSuccess(order);
    }
}

