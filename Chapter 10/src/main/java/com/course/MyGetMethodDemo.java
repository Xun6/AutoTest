package com.course;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

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


    /**
     * 这是一个需要携带cookies信息才能访问的get请求
     * 需要客户端携带cookies访问
     * */

    @RequestMapping(value="/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();    //获取cookies
        //判断cookies为空
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息来访问";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "恭喜你，携带cookies信息的get请求访问成功！！！";
            }
        }

        return "你必须携带cookies信息进行访问！";
    }
}
