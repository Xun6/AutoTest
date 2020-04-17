package com.source.testng.groupClass;

import org.testng.annotations.Test;

@Test(groups = "Teacher")
public class groupOfTeacher {
    public void teacher(){
        System.out.println("groupOfTeacher 中的 teacher 方法运行了！！");
    }
}
