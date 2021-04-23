package com.zzx.center.service;

import com.zzx.center.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.center.entity.vo.RegisterVO;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zzx
 * @since 2021-04-21
 */
public interface MemberService extends IService<Member> {

    /**
     * 登录
     *
     * @param member
     * @return
     */
    String login(Member member);

    /**
     * 注册
     *
     * @param registerVO
     */
    void register(RegisterVO registerVO);

    /**
     * 根据微信openid查询member
     * @param openid
     * @return
     */
    Member selectMemberByOpenid(String openid);
}
