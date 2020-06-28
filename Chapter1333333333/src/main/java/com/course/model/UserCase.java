package com.course.model;

import lombok.Data;


@Data
public class UserCase {
    /*
    匹配数据库中 UserCase 表中对应的字段
    * */
    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;
}
