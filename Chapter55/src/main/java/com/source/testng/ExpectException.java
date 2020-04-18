package com.source.testng;


import org.testng.annotations.Test;

/**
 * 这是一个 异常测试*/
public class ExpectException {


    // 失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void RuntimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }



    //成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void RuntimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();      // 抛出异常
    }
}
