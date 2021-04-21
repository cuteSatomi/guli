package com.zzx.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.cms.entity.Banner;
import com.zzx.cms.mapper.BannerMapper;
import com.zzx.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-20
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<Banner> selectBanner() {
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<Banner>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<Banner> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
