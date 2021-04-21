package com.zzx.center.service.impl;

import com.zzx.center.entity.Member;
import com.zzx.center.mapper.MemberMapper;
import com.zzx.center.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
