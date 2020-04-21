package com.source.testng.multiThread;


import org.testng.annotations.Test;

/**
 * 这是一个
 * 多线程测试*/
public class multiThreadTest {

    @Test(invocationCount = 10,threadPoolSize = 3)
    public void testMethod(){
        System.out.println(1);
        System.out.println("Thread Id : %s%n"+Thread.currentThread().getId());
    }
}
