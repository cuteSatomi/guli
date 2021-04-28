package com.zzx.statistics.client;

import com.zzx.utils.ResultTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzx
 * @date 2021-04-26 17:17:43
 */
@Component
@FeignClient(name = "service-center")
public interface MemberClient {

    @GetMapping("/center/member/countRegister/{day}")
    ResultTO countRegister(@PathVariable("day") String day);
}
