package com.zzx.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzx
 * @date 2021-04-26 15:46:13
 */
@Component
@FeignClient(name = "service-order")
public interface OrderClient {
    @GetMapping("/edu/order/courseIsBuy/{courseId}/{memberId}")
    boolean courseIsBuy(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
