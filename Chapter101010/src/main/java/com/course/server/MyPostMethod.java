package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求！！！")
@RequestMapping("/v1")   //表示以下所有的请求url前都要加上这个
public class MyPostMethod {

    //这个变量用来装我们的cookie信息
    private static Cookie cookie;

    //例如：用户登录成功获取cookies，然后再访问其他接口获取列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){

        //判断
        if(username.equals("zhangsan")&& password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了！！！";
        }
        return "用户名或密码错误";   //不满足条件时返回这条信息

    }
}
