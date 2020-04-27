package com.httpclient.demo.cookiesForProperties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class cookiesForGet {

    private String url;
    private ResourceBundle bundle;   // 自带的java.util包中的

    @BeforeTest
    public void beforeTest(){
        //默认识别 .properties配置文件，Locale.CHINA解决字符编码的问题
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url =bundle.getString("test,url");    //取出配置文件内的 url
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri=bundle.getString("getCookies.uri");  //取出配置文件内的 uri
        String testUrl=this.url+uri;  //拼接地址

        // 测试访问逻辑
        HttpGet get = new HttpGet(testUrl);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
