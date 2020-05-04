package com.course;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyGetMethodDemo {
/**
 * value= 表示访问路径
 * method= 表示访问方法*/
    @RequestMapping(value="/getCookies", method= RequestMethod.GET)
    public String getBackCookies(HttpServletResponse response){
        //HttpServerletRequest   装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获取cookies信息成功";
    }
}
