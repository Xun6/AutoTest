package com.xiaoyu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}