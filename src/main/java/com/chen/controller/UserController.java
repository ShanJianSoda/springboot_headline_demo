package com.chen.controller;


import com.chen.pojo.User;
import com.chen.service.UserService;
import com.chen.utils.JwtHelper;
import com.chen.utils.Result;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//处理HTTP请求并返回JSON格式
@RequestMapping("user")//任意HTTP 请求映射到控制器方法
@CrossOrigin//跨域响应
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        System.out.println("result: " + result);
        return result;
    }

    //    @GetMapping("getUserInfo")
//    public Result getUserInfo(@RequestHeader String token) {
//        Result result = userService.getUserInfo(token);
//        System.out.println("result: " + result);
//        return result;
//    }
    @GetMapping("getUserInfo")
    public Result userInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username) {
        Result result = userService.checkUserName(username);
        System.out.println("result: " + result);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        System.out.println("result: " + result);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(String token) {
        Result result = userService.checkLogin(token);
        return result;
    }


}
