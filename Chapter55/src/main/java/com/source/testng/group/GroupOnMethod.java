package com.source.testng.group;


import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;


// 这是组测试中的 方法分组测试
public class GroupOnMethod {
    @Test(groups = "server")       //groups 组标签
    public void test1(){
        System.out.println(" 这是server 端分组");
    }
    @Test(groups = "client")
    public void test2(){
        System.out.println("这是 client 端分组");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("在server端组运行之前运行的方法！！！");
    }

    @AfterGroups("server")
    public void afterGroupOnServer(){
        System.out.println("在server端组运行之后运行的方法！！！");
    }
}
