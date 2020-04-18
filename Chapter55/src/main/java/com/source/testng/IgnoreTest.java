package com.source.testng;

import org.testng.annotations.Test;

/**这是一个忽略测试*/
public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 执行了");
    }

    @Test(enabled = false)      //该测试用例被忽略了
    public void ignore2(){
        System.out.println("ignore2 执行了");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.println("ignore3 执行了");
    }
}
