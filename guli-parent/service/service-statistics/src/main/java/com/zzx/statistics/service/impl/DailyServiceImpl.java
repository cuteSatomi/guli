package com.zzx.statistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.statistics.client.MemberClient;
import com.zzx.statistics.entity.Daily;
import com.zzx.statistics.mapper.DailyMapper;
import com.zzx.statistics.service.DailyService;
import com.zzx.utils.ResultTO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-26
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {
    @Autowired
    private MemberClient memberClient;

    @Override
    public void countRegister(String day) {
        ResultTO resultTO = memberClient.countRegister(day);
        Integer count = (Integer) resultTO.getData();

        Daily daily = new Daily();
        // 注册人数
        daily.setRegisterNum(count);
        // 统计日期
        daily.setDateCalculated(day);

        // 其他的数据用随机数模拟
        daily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        daily.setLoginNum(RandomUtils.nextInt(100, 200));
        daily.setCourseNum(RandomUtils.nextInt(100, 200));

        baseMapper.insert(daily);
    }
}
