package com.zzx.edu.controller;

import com.zzx.utils.ResultTO;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-04-12 15:44:44
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/user")
public class LoginController {
    /**
     * 模拟登录功能
     * @return
     */
    @PostMapping("/login")
    public ResultTO login(){
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return ResultTO.buildSuccess(map);
    }

    @GetMapping("/info")
    public ResultTO info(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return ResultTO.buildSuccess(map);
    }
}
