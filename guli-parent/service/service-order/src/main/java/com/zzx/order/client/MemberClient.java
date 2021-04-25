package com.zzx.order.client;

import com.zzx.utils.ordervo.OrderMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzx
 * @date 2021-04-25 20:37
 */
@Component
@FeignClient(name = "service-center")
public interface MemberClient {

    /**
     * 根据用户id查询用户相关信息
     * @param memberId
     * @return
     */
    @GetMapping("/center/member/getOrderMemberInfo/{memberId}")
    public OrderMember getOrderMemberInfo(@PathVariable("memberId") String memberId);
}
