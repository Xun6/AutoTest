package com.httpclient.Demo;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class postWithCookies {

    private String url;
    private ResourceBundle bundle;
    //定义储存cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void testBeforeMethod(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }


    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("testGetBackCookies.uri");
        //拼接地址
        String testUrl = this.url+uri;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(testUrl);
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);


        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+ name+" ;cookie value = "+value);
        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})  //依赖 testGetCookies 方法执行
    public void testGetWithCookie() throws IOException {
        String result;
        String uri = bundle.getString("testGetWithCookies.uri");
        String TestUrl = this.url+uri;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(TestUrl);
        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);
        if(statusCode == 200){
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }

    }
}
