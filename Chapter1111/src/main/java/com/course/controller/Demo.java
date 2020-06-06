package com.course.controller;

import com.course.module.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value="v1",description="这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {

    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    //执行 sql语句的方法
    @RequestMapping(value="/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value="获取用户数",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount2");    //其中getUserCount2 是我的 mysql.xml 文件内的id
    }


    //往数据库内添加数据
    @RequestMapping(value = "/addUserData",method = RequestMethod.POST)
    @ApiOperation(value="添加用户",httpMethod = "POST")
    //使用@Requestbody 把 User 类里的数据传进来
    public int addUser(@RequestBody User user){
        int result = template.insert("addUser2",user);    //向表中插入数据
        return result;
    }


    //更新数据库内的数据
    @RequestMapping(value = "/updateUserData",method = RequestMethod.POST)
    @ApiOperation(value = "编辑更新用户",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUserData2",user);
    }


    //删除数据库内数据
    @RequestMapping(value = "/delUserData",method = RequestMethod.GET)
    @ApiOperation(value ="删除用户",httpMethod = "GET")
    public int delUser(@RequestParam int id){
        return template.delete("delUserData2",id);
    }


}
