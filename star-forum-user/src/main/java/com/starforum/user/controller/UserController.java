package com.starforum.user.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.user.entity.User;
import com.starforum.user.service.UserService;
import com.starforum.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StateCode.SUCCESS, "查询成功", userService.findAll());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        User userLogin = userService.login(user);
        if(userLogin == null){
            return new Result(false, StateCode.LOGIN_ERROR, "登录失败，用户名或密码错误");
        }

        String token = jwtUtil.createJWT(userLogin.getId(), userLogin.getMobile(), "user");
        return new Result(true, StateCode.SUCCESS, "登录成功", token);
    }

}
