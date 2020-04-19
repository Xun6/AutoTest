package com.source.testng.MultiThread;

import org.testng.annotations.Test;

/**
 * 这是一个 通过 xml 文件实现的 多线程测试*/
public class multiThreadOnXml {

    @Test
    public void ThreadTestMethod1(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void ThreadTestMethod2(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void ThreadTestMethod3(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
