package com.xiaoyu.cases;

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.GetUserInfoCase;
import com.xiaoyu.modul.UserCase;
import com.xiaoyu.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "Login",description = "获取 userId 为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession(); //定义、调用我的sql语句执行方法类
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase1",1); //定义我的modul中的 GetUserInfoCase 类对象的引用变量，并执行配置文件（SQLMapper.xml）中的sql语句（按id匹配），赋值给变量
        System.out.println(getUserInfoCase.toString()); //打印 getUserInfoCase结果
        System.out.println(TestConfig.getUserInfoUrl); // 打印接口地址（url）


        //发送请求，获取结果
        JSONArray resultJson = getJsonResult(getUserInfoCase);  //响应结果是json格式，所有用 JSONArray resultJson 来接收

//        Thread.sleep(2000);
        UserCase user = session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase); //调sql查询数据库
//        System.out.println("自己查库获取用户信息："+user.toString());
        List userList = new ArrayList();
        //把查询出来的 user放入
        userList.add(user);
        //把 查询结果 user 转换成 JSONArray
        JSONArray jsonArray = new JSONArray(userList);
        //判断请求获得结果，和自己查询的结果是否一致
        Assert.assertEquals(jsonArray,resultJson);
    }



    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();  //转换参数为 json格式
        param.put("id",getUserInfoCase.getUserId());
        //设置请求头 header
        post.setHeader("content-type","application/json");
        //将参数信息加入到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8"); //实例化请求体，并传入设置的参数
        post.setEntity(entity);   //调用 setEntity()方法，使post请求体生效
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        //把响应结果转换成 list
        List resultList = Arrays.asList(result);
        //把 resultList 转换成 JSONArray
        JSONArray array = new JSONArray(resultList);
        return array;  //返回 array

    }
}
