package com.course.controller;


import com.course.model.UserCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;


/*
接口开发类 UserManager
*/
@Log4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")   //根路径
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;  //访问数据库的对象


    /**
     * 登录接口
     * */
    @ApiOperation(value ="登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)   //访问路径
    public boolean Login(HttpServletResponse response, @RequestBody UserCase userCase){
        //查询数据库，匹配是否存在一条满足我请求的用户参数
        int u = template.selectOne("login", userCase);
        //设置cookies信息
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);   //响应结果返回cookie
        log.info("查询到的结果是"+u);  //打印log信息
        if(u==1){
            log.info("登录的用户是："+ userCase.getUserName());   //判断为 1 时，打印出查询的用户名
            return true;
        }
        return false;
    }


    /**
     * 添加用户接口
     * */
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")      //接口描述
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)  //接口路径
    //需要带cookies请求，且传参必须满足 UserCase类中的设定参数
    public boolean addUser(HttpServletRequest request,@RequestBody UserCase userCase){
        Boolean c = verifyCookies(request);  //验证传入的cookies,调用下方的 verifyCookies()验证方法
        int result= 0;  //声明一个 result变量
        if(c!=null){
            result = template.insert("addUser", userCase);  //执行sql语句，添加用户
        }
        //判断若查询结果存在
        if(result > 0){
            log.info("添加用户的数量是："+result);  //打印出log信息
            return true;   //返回成功
        }
        return false;  //以上均不满足，则返回失败
    }


    /**
     * 获取用户（列表）信息接口
     * */
    @ApiOperation(value="获取用户（列表）信息接口",httpMethod = "POST")
    @RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
    //泛型<UserCase>
    public List<UserCase> getUserInfo(HttpServletRequest request, @RequestBody UserCase userCase){
        Boolean d = verifyCookies(request);  //验证cookies
        //若cookies验证通过
        if(d==true){
            List<UserCase> users = template.selectList("getUserInfo", userCase); //执行sql语句进行查询
            log.info("获取到的用户数量是："+ users.size());  //打印出log信息
            return users;   //返回查询到的用户信息
        }else{
            return null; //以上不满足则返回null
        }
    }


    /**
     * 更新/删除用户接口
     * */
    @ApiOperation(value="更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(value ="/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody UserCase userCase){
        boolean e = verifyCookies(request);
        int i = 0;
        if(e==true){
            i = template.update("updateUserInfo", userCase);
        }
        log.info("更新数据的条目数："+i);   //打印出log信息
        return i;   //返回条目数  若为1 表示更新成功
    }

    //抽取出来的，验证 cookies信息的方法
    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();  //声明一个数组引用来接收请求参数获取的cookies信息
        if(Objects.isNull(cookies)){
            log.info("cookies为空了！");  //打印出log信息
            return false;
        }
        //循环遍历cookies，判断信息是否正确
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                log.info("cookie验证通过");  //打印出log信息
                return true;
            }
        }
        return false;
    }
}
