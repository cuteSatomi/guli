package com.zzx.center.mapper;

import com.zzx.center.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2021-04-21
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 查询某一天的注册人数
     *
     * @return
     */
    Integer countRegister(String day);
}
