package com.starforum.user.controller;

import com.starforum.entity.Result;
import com.starforum.entity.StateCode;
import com.starforum.user.entity.User;
import com.starforum.user.service.UserService;
import com.starforum.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

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
        Map map = new HashMap();
        map.put("token", token);
        map.put("user", userLogin);
        return new Result(true, StateCode.SUCCESS, "登录成功", map);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Result checkLogin(){
        String token = request.getHeader("Authorization");
        try {
            Claims claims = jwtUtil.parseJWT(token);
        }catch (ExpiredJwtException eje) {
            return new Result(false, StateCode.UNAUTHORIZED, "用户登录超时", eje);
        } catch (Exception e){
            return new Result(false, StateCode.VALIDATE_FAILED, "token错误", e);
        }
        return new Result(true, StateCode.SUCCESS, "验证成功");
    }
}
