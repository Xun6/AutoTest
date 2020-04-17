package com.source.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAnnotation {

    @Test
    public void testCase1(){
        System.out.println("这是测试用例 1");
    }


    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是 BeforeMethod 方法");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("这是 AfterMethod 方法");
    }
}
