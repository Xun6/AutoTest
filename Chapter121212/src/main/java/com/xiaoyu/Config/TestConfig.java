package com.xiaoyu.Config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 放置一些变量，与配置文件 application.properties 中的对应
 * */
public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;

    public static DefaultHttpClient defaultHttpClient;      //在此定义一下 httpclient对象引用变量 ，方便以后调用
    public static CookieStore store;                   //在此定义一下 Cookiestore对象引用变量 ,方便以后调用
}
