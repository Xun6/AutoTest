package com.source.testng;


import org.testng.annotations.Test;

/**
 * 这是一个 超时测试*/
public class TimeOutTest {

    @Test(timeOut = 3000)   //单位为毫秒值
    public void timeOutSuccess() throws InterruptedException {
        Thread.sleep(2000);     //设置等待时间
    }


    @Test(timeOut = 3000)   //  期望在 3s 内执行完成，否则抛出异常
    public void timeOutFailed() throws InterruptedException {
        Thread.sleep(4000);     //设置等待时间
    }
}
