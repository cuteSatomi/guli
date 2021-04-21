package com.zzx.cms.controller;

import com.zzx.cms.entity.Banner;
import com.zzx.cms.service.BannerService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzx
 * @date 2021-04-20 22:03
 */
@CrossOrigin
@RestController
@RequestMapping("/cms/banner/front")
public class BannerFrontController {
    @Autowired
    private BannerService bannerService;

    /**
     * 查询所有banner，用于首页的显示
     * @return
     */
    @GetMapping("/listBanner")
    public ResultTO listBanner() {
        List<Banner> bannerList = bannerService.selectBanner();
        return ResultTO.buildSuccess(bannerList);
    }
}
