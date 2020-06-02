package com.xiaoyu.modul;

import lombok.Data;

@Data
public class LoginCase {
    private int id;
    private String userName;
    private String password;
    private String expected;

    public String getExpected() {
        return expected;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
