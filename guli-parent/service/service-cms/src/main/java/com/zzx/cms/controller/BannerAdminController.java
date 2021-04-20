package com.zzx.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzx.cms.entity.Banner;
import com.zzx.cms.service.BannerService;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-20
 */
@CrossOrigin
@RestController
@RequestMapping("/cms/banner/admin")
public class BannerAdminController {

    @Autowired
    private BannerService bannerService;

    /**
     * 分页查询banner
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/pageBanner/{current}/{size}")
    public ResultTO pageBanner(@PathVariable long current, @PathVariable long size) {
        Page<Banner> page = new Page<Banner>(current, size);
        bannerService.page(page, null);
        return ResultTO.buildSuccess(page);
    }

    /**
     * 添加banner
     *
     * @param banner
     * @return
     */
    @PostMapping("/addBanner")
    public ResultTO addBanner(@RequestBody Banner banner) {
        bannerService.save(banner);
        return ResultTO.buildSuccess("添加banner成功");
    }

    /**
     * 更新banner
     *
     * @param banner
     * @return
     */
    @PostMapping("/updateBanner")
    public ResultTO updateBanner(@RequestBody Banner banner) {
        bannerService.updateById(banner);
        return ResultTO.buildSuccess("更新banner成功");
    }

    /**
     * 删除banner
     *
     * @param bannerId
     * @return
     */
    @DeleteMapping("/{bannerId}")
    public ResultTO updateBanner(@PathVariable String bannerId) {
        bannerService.removeById(bannerId);
        return ResultTO.buildSuccess("删除banner成功");
    }

    @GetMapping("/get/{bannerId}")
    public ResultTO getBannerById(@PathVariable String bannerId) {
        Banner banner = bannerService.getById(bannerId);
        return ResultTO.buildSuccess(banner);
    }
}

