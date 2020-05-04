package com.course;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
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


    /**
     * 开发一个需要参数才能访问的 get 请求
     * 第一种实现方式 url：key=value & key=value
     * 我们来模拟商品列表
     * */
    @RequestMapping(value="/get/with/param",method=RequestMethod.GET)
    public Map getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> mylist = new HashMap<>();

        mylist.put("鞋子",350);
        mylist.put("衬衫",300);
        mylist.put("裤子",100);
        return mylist;
    }


    /**
     * 第二种需要参数才能访问的 get 请求
     * url: ip:port/get/with/param/10/20
     * */

    @RequestMapping(value="/get/with/param/{start}/{end}")
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋子",350);
        myList.put("衬衫",300);
        myList.put("裤子",100);
        return myList;

    }

}
