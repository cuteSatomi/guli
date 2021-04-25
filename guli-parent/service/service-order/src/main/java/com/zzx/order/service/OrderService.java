package com.zzx.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.order.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据课程id和用户id远程调用得到数据
     * @param courseId
     * @param memberId
     * @return
     */
    String createOrder(String courseId, String memberId);
}
