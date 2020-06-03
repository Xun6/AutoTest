package com.xiaoyu.modul;

import lombok.Data;

@Data
public class UserCase {

    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;


    //重写一个 toString方法,把字段按照json格式进行拼接
    @Override
    public String toString(){
        return(
            "{id"+id+","+
            "userName"+userName+","+
            "password"+password+","+
            "age"+age+","+
            "sex"+sex+","+
            "permission"+permission+","+
            "isDelete"+isDelete+"}"
                );

    }

}
