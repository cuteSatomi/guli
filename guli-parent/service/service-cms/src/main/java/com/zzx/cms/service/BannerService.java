package com.zzx.cms.service;

import com.zzx.cms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-20
 */
public interface BannerService extends IService<Banner> {

    /**
     * 查询banner
     * @return
     */
    List<Banner> selectBanner();
}
