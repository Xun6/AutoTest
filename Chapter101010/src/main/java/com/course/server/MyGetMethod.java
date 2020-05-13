package com.course.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * 开发一个需要携带参数才能访问的 get 请求
     * 第一种实现方式 url: key=value & key=value
     * 例如模拟获取商品列表
     * */
    @RequestMapping(value ="/get/with/param",method = RequestMethod.GET)
    public Map<String,Integer> getlist(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> mylist = new HashMap<>();
        mylist.put("鞋子",400);
        mylist.put("chengshan",300);
        mylist.put("wanzi",900);

        return mylist;

    }

    /**
     * 第二种带参数get请求实现方法
     * url:ip:port/get/with/param/10/20
     * */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,String> list = new HashMap<>();
        list.put("年龄","18");
        list.put("姓名","小鱼");
        list.put("性别","男");

        return list;

    }
}
