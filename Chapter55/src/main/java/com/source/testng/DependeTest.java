package com.source.testng;

import org.testng.annotations.Test;

/**
 * 这是一个 依赖测试
 * */
public class DependeTest {

    @Test
    public void testMethod1(){
        System.out.println("run testMethod1");
//        throw new RuntimeException();         //若test1 运行异常，test2 会被忽略掉
    }


    @Test(dependsOnMethods = {"testMethod1"})      //依赖testMethod1,运行testMethod2 时 testMethod1 也运行
    public void testMethod2(){
        System.out.println("run testMethod2");
    }
}
