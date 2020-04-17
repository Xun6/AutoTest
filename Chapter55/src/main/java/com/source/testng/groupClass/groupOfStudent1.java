package com.source.testng.groupClass;

import org.testng.annotations.Test;

@Test(groups = "Student1")
public class groupOfStudent1 {

    public void student11(){
        System.out.println("groupOfStudent1 中的 student11方法运行了");
    }
}
