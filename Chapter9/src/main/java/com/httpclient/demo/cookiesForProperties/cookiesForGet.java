package com.httpclient.demo.cookiesForProperties;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class cookiesForGet {

    private String url;
    private ResourceBundle bundle;   // 自带的java.util包中的
    private CookieStore store;    //声明一个用来存储 cookies 信息的变量

    @BeforeTest
    public void beforeTest(){
        //默认识别 .properties配置文件，Locale.CHINA解决字符编码的问题
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("test.url");    //取出配置文件内的 url
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");  //取出配置文件内的 uri
        String testUrl = this.url+uri;  //拼接地址

        // 测试访问逻辑
        HttpGet get = new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();      //必须是DefaultHttpClient获取cookies信息
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        //获取 cookies 信息
//        CookieStore store = client.getCookieStore();  //变更前
        this.store = client.getCookieStore();     //变更后
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name ="+name+"; cookie value ="+value);
        }
    }


    @Test(dependsOnMethods = {"testGetCookies"})
    public void getWithCookies() throws IOException {
        String uri = bundle.getString("getWithCookies.uri");
        String TestUrl = this.url+uri;

        HttpGet get = new HttpGet(TestUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置 cookies 信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);   //接收执行后的响应
         //获取响应的状态吗
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);

        if(statusCode == 200){
            String result = EntityUtils.toString(response.getEntity());    //把获得的响应实体内容赋值给 变量 result
            System.out.println(result);
        }


    }

}
