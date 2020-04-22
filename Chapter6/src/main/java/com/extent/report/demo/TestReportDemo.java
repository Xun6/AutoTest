package com.extent.report.demo;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * 这是一个 extentReport 基本配置演练*/
public class TestReportDemo {

    @Test
    public void testMethod1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void testMethod2(){
        Assert.assertEquals(2,1);
    }

    @Test
    public void testMethod3(){
        Assert.assertEquals("aaa","aaa");
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("这是运行时抛出的异常");
    }
}
