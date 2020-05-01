package com.httpclient.demo.cookiesForProperties;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class cookiesForPost {
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
        String uri = bundle.getString("Get.Cookies.uri");  //取出配置文件内的 uri
        String testUrl = this.url + uri;  //拼接地址

        // 测试访问逻辑
        HttpGet get = new HttpGet(testUrl);
//        HttpClient client = new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();      //必须是DefaultHttpClient获取cookies信息
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);


        //获取 cookies 信息
//        CookieStore store = client.getCookieStore();  //变更前
        this.store = client.getCookieStore();     //变更后
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name =" + name + "; cookie value =" + value);
        }
    }


    @Test(dependsOnMethods = {"testGetCookies"})
    public void postWithCookies() throws IOException {
        String uri = bundle.getString("postWithCookies.uri");
        //拼接最终的测试地址
        String testUrl = this.url + uri;

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个 post 方法
        HttpPost post = new HttpPost(testUrl);
        //添加请求参数（json格式）
        JSONObject param = new JSONObject();
        param.put("name","yuxunzhi");
        param.put("age","20");
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");   //会抛出一个异常
        post.setEntity(entity);
        //声明一个对象来存储响应结果
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
        JSONObject Result = new JSONObject(result);
        //获取结果值
        String huhansan = (String)Result.get("huhansan");   //强转成 String类型
        String status = (String)Result.get("status");
        String text = (String)Result.get("text");
        //断言判断返回结果的值
        Assert.assertEquals("success",huhansan);
        Assert.assertEquals("1",status);
        Assert.assertEquals("我的带cookies的post请求访问成功了！！！",text);
    }
}
