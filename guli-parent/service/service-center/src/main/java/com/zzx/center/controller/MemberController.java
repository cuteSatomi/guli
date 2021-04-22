package com.zzx.center.controller;


import com.zzx.center.entity.Member;
import com.zzx.center.entity.vo.RegisterVO;
import com.zzx.center.service.MemberService;
import com.zzx.utils.JwtUtils;
import com.zzx.utils.ResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2021-04-21
 */
@CrossOrigin
@RestController
@RequestMapping("/center/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 登录
     *
     * @param member
     * @return
     */
    @PostMapping("/login")
    public ResultTO login(@RequestBody Member member) {
        String token = memberService.login(member);
        return ResultTO.buildSuccess(token);
    }

    /**
     * 注册
     *
     * @param registerVO
     * @return
     */
    @PostMapping("/register")
    public ResultTO register(@RequestBody RegisterVO registerVO) {
        memberService.register(registerVO);
        return ResultTO.buildSuccess(null);
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/getMemberInfo")
    public ResultTO getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return ResultTO.buildSuccess(member);
    }
}

