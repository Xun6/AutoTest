package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

//以下是我的所有 Get方法的类
@RestController      //表示我是被扫描的类
public class MyGetMethod {

    @RequestMapping(value = "/get/back/cookies",method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){     //装响应信息的类
        Cookie cookies = new Cookie("token","true");
        response.addCookie(cookies);   //获取响应返回的cookies
        return "恭喜你获得cookies信息成功！";
    }



    /**
     * 要求客户端携带cookies访问
     * */
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        if(Objects.isNull(cookie)){
            return "你必须携带cookies信息来访问！!!!!!";
        }
        for(Cookie cookies : cookie){
            if(cookies.getName().equals("token")&&cookies.getValue().equals("true")){
                return "恭喜你，携带cookies访问成功了！！嘿嘿";
            }
        }
        return "你必须携带cookies信息来访问！";
    }
}
