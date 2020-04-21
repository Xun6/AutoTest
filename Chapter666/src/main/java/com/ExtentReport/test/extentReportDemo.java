package com.ExtentReport.test;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class extentReportDemo {

    @Test
    public void testMethod1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void testMethod2(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void testMethod3(){
        Assert.assertEquals("qqq","qqq");
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我自己的日志信息");
        throw new RuntimeException("这是运行时抛出的异常！！！");
    }
}
