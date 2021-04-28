package com.zzx.statistics.service;

import com.zzx.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-26
 */
public interface DailyService extends IService<Daily> {

    /**
     * 查询某一天的注册人数
     * @param day
     */
    void countRegister(String day);
}
