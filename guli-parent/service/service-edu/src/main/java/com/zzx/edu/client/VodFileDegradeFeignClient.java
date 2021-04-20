package com.zzx.edu.client;

import com.zzx.utils.ResultTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zzx
 * @date 2021-04-20 16:13:26
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public ResultTO removeVideoById(String videoId) {
        return ResultTO.buildFailed("熔断器：删除视频失败");
    }

    @Override
    public ResultTO deleteBatch(List<String> videoIdList) {
        return ResultTO.buildFailed("批量删除视频失败");
    }
}
