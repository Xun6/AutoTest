package com.source.testng.Parameter;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 这是一个 参数化测试
 * */
public class ParameterTest {

    @Test
    @Parameters({"name","age"})             //引入 xml文件内的 参数
    public void paramTestMethod(String name,int age){
        System.out.println("name = "+ name + "\nage = "+ age);
    }
}
