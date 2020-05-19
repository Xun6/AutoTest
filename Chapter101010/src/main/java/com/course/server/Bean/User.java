package com.course.server.Bean;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String password;
    private String name;
    private String age;
    private String sex;

    public String getPassword() {
        return password;
    }

    public String getuserName() {
        return userName;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setAge(String age) {
        this.age=age;
    }

    public void setSex(String sex) {
        this.sex=sex;
    }
}
