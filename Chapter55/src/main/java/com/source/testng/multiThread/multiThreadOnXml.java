package com.source.testng.multiThread;


import org.testng.annotations.Test;

/**
 * 这是一个 xml文件实现的多线程测试*/
public class multiThreadOnXml {

    @Test
    public void test1(){
        System.out.println("Thread Id : %s%n"+Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.println("Thread Id : %s%n"+Thread.currentThread().getId());
    }
}
