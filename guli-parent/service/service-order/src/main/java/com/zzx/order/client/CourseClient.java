package com.zzx.order.client;

import com.zzx.utils.ordervo.OrderCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzx
 * @date 2021-04-25 20:37
 */
@Component
@FeignClient(name = "service-edu")
public interface CourseClient {

    /**
     * 根据课程id查询课程相关信息
     * @param courseId
     * @return
     */
    @GetMapping("/edu/service/front/course/getOrderCourseInfo/{courseId}")
    public OrderCourse getOrderCourseInfo(@PathVariable("courseId") String courseId);
}
