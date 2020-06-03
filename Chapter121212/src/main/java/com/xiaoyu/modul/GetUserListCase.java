package com.xiaoyu.modul;

import lombok.Data;

@Data
public class GetUserListCase {
    private String userName;
    private String age;
    private String sex;
    private String expected;

    public String getUserName() {
        return userName;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getExpected() {
        return expected;
    }
}
