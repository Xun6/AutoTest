package com.xiaoyu.cases;

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.UpdateUserInfoCase;
import com.xiaoyu.modul.UserCase;
import com.xiaoyu.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInforTest {


    @Test(dependsOnGroups = "Login",description = "更改用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase1",1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        //发送请求，获取结果，定义一个 result 来接收
        int result = getResult(updateUserInfoCase);

        Thread.sleep(5000);  //解决上方请求接口线程没有跑完，就执行了下方的查询语句，产生报错
        //执行sql语句，查询数据库
        UserCase user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        //判断查询结果 user不为空
        Assert.assertNotNull(user);
        //判断请求返回结果不为空
        Assert.assertNotNull(result);

    }

    @Test(dependsOnGroups = "Login",description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase1",2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);


        //发送请求，获取结果，定义一个 result 来接收
        int result = getResult(updateUserInfoCase);

        Thread.sleep(5000);  //解决上方请求接口线程没有跑完，就执行了下方的查询语句，产生报错
        //执行sql语句，查询数据库
        UserCase user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        //判断查询结果 user不为空
        Assert.assertNotNull(user);
        //判断请求返回结果不为空
        Assert.assertNotNull(result);
    }


    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject(); //转换参数为json格式
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return Integer.parseInt(result);   //将字符串解析为 Int 类型
    }
}
