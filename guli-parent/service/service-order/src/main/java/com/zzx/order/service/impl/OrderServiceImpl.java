package com.zzx.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.order.client.CourseClient;
import com.zzx.order.client.MemberClient;
import com.zzx.order.entity.Order;
import com.zzx.order.mapper.OrderMapper;
import com.zzx.order.service.OrderService;
import com.zzx.order.utils.OrderNoUtil;
import com.zzx.utils.ordervo.OrderCourse;
import com.zzx.utils.ordervo.OrderMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private CourseClient courseClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        // 根据课程id查询课程信息
        OrderCourse orderCourseInfo = courseClient.getOrderCourseInfo(courseId);
        OrderMember orderMemberInfo = memberClient.getOrderMemberInfo(memberId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(orderCourseInfo.getId());
        order.setCourseTitle(orderCourseInfo.getTitle());
        order.setCourseCover(orderCourseInfo.getCover());
        order.setTeacherName(orderCourseInfo.getTeacherName());
        order.setMemberId(memberId);
        order.setNickname(orderMemberInfo.getNickname());
        order.setMobile(orderMemberInfo.getMobile());
        order.setTotalFee(orderCourseInfo.getPrice());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
