package com.zzx.statistics.controller;


import com.zzx.statistics.service.DailyService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-26
 */
@CrossOrigin
@RestController
@RequestMapping("/statistics/daily")
public class DailyController {
    @Autowired
    private DailyService dailyService;

    /**
     * 统计某一天的注册人数
     *
     * @param day
     * @return
     */
    @GetMapping("/countRegister/{day}")
    public ResultTO countRegister(@PathVariable String day) {
        dailyService.countRegister(day);
        return ResultTO.buildSuccess(null);
    }
}

