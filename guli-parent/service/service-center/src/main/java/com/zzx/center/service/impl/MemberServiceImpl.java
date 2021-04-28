package com.zzx.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.base.exception.GuliException;
import com.zzx.center.entity.Member;
import com.zzx.center.entity.vo.RegisterVO;
import com.zzx.center.mapper.MemberMapper;
import com.zzx.center.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.utils.JwtUtils;
import com.zzx.utils.MD5;
import com.zzx.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2021-04-21
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

        // 手机号码和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(ResultCode.ERROR, "用户名或密码为空");
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("mobile", mobile);
        Member dbMember = baseMapper.selectOne(queryWrapper);

        if (dbMember == null) {
            throw new GuliException(ResultCode.ERROR, "该手机号码尚未注册");
        }

        if (!MD5.encrypt(password).equals(dbMember.getPassword())) {
            throw new GuliException(ResultCode.ERROR, "用户名或密码错误");
        }

        if (dbMember.getIsDisabled()) {
            throw new GuliException(ResultCode.ERROR, "该账号已禁用，请联系管理员");
        }

        return JwtUtils.getJwtToken(dbMember.getId(), dbMember.getNickname());
    }

    @Override
    public void register(RegisterVO registerVO) {
        String nickname = registerVO.getNickname();
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new GuliException(ResultCode.ERROR, "缺少必填信息，注册失败");
        }

        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (redisCode == null) {
            throw new GuliException(ResultCode.ERROR, "验证码过期，注册失败");
        }

        if (!redisCode.equals(code)) {
            throw new GuliException(ResultCode.ERROR, "验证码错误，注册失败");
        }

        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.bqatj.com%2Fimg%2F7839ff9a8e074306.jpg&refer=http%3A%2F%2Fimg.bqatj.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621662516&t=fe034a86594eaf941106102d0ff5a94a");
        baseMapper.insert(member);
    }

    @Override
    public Member selectMemberByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("openid", openid);
        Member member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
