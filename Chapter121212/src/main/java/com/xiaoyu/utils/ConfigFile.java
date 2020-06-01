package com.xiaoyu.utils;      //这是一个工具包

import com.xiaoyu.modul.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 这是一个工具类，完成拼接 url 的工作
 * */
public class ConfigFile {
    //工具类一般使用静态方法（static），不用 new 直接使用
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name){   //传参必须满足我的枚举类（InterfaceName）的要求
        String hostAddress = bundle.getString("test.url");   //映射配置文件中的地址
        String uri="";
        String testUrl;   //表示最终完成拼接的 url
        if(name == InterfaceName.ADDUSERINFO){
            uri = bundle.getString("addUser.uri");
        }
        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        if(name == InterfaceName.GETUSERLIST){
            uri =bundle.getString("getUserlist.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        testUrl = hostAddress + uri;

        return testUrl;

    }


}
