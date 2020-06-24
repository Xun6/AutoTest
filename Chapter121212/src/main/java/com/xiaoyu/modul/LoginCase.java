package com.xiaoyu.modul;

import lombok.Data;


/**
 * 其他case的格式也都一样。
 * 在这里参照mysql数据库表里配置的字段一一进行声明。
 * */

@Data
public class LoginCase {
    private int id;
    private String userName;
    private String password;
    private String expected;

    /*public String getExpected() {
        return expected;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }*/
}
