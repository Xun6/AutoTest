package com.source.testng.Suite;

import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeMethod
    public void BeforeMethod(){
        System.out.print("BeforeMethod 方法运行了");
    }
    @AfterMethod
    public void AfterMethod(){
        System.out.print("AfterMethod 方法运行了");
    }
    @BeforeTest
    public void BeforeTest(){
        System.out.print("BeforeTest 方法运行了");
    }
    @AfterTest
    public void AfterTest(){
        System.out.print("AfterTest 方法运行了");
    }
    @BeforeSuite
    public void BeforeSuite(){
        System.out.print("BeforeSuite 方法运行了");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.print("AfterSuite 方法运行了");
    }
}
