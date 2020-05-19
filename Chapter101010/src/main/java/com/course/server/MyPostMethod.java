package com.course.server;


import com.course.server.Bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserlist(HttpServletRequest request, @RequestBody User user){

        //定义我的类对象变量
        User u;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies和用户名、密码是否合法
        for(Cookie c : cookies){
            if(c.getName().equals("login")&&c.getValue().equals("true")
            &&user.getuserName().equals("zhangsan")
            &&user.getPassword().equals("123456")){
                //实例化我自己的 User对象，返回我的信息
                 u = new User();
                 u.setName("xiaoyu");
                 u.setAge("18");
                 u.setSex("男");
                 return u.toString();
            }
        }
        return "参数不合法！！！";   //若上述验证不通过，返回此信息
    }
}
