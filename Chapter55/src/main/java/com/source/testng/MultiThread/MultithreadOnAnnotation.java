package com.source.testng.MultiThread;


import org.testng.annotations.Test;

import static java.lang.Thread.currentThread;

/**
 * 这是一个 注解实现的多线程测试*/
public class MultithreadOnAnnotation {

    @Test(invocationCount = 10,threadPoolSize = 3)      //调用 10次，线程池 3个
    public void multiThreadMethod(){
        System.out.println("1");
        System.out.printf("Thread Id: %s%n", currentThread().getId());
    }
}
