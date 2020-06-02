package com.xiaoyu.cases;

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.AddUserCase;
import com.xiaoyu.modul.User;
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

public class AddUserTest {

    @Test(dependsOnGroups = "Login",description = "添加用户接口接口")   //依赖于“groups”组
    public void addUser() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();  //定义、调用我的sql语句执行方法类
        AddUserCase addUserCase = session.selectOne("addUserCase1",1);  //实例化我的modul中的AddUserCase类对象，并执行配置文件（SQLMapper.xml）中的sql语句（按id匹配）
        System.out.println(addUserCase.toString());   //打印 addUserCase 的结果
        System.out.println(TestConfig.addUserUrl);  //打印 添加用户接口地址（url）

        //下面的代码为写完接口的测试代码
        String result = getResult(addUserCase);   //定义结果变量（result），调用下面创建的 getResult()方法，且传参需满足 addUserCase 类中的字段

        //查询用户看是否添加成功
        Thread.sleep(2000);
        User user = session.selectOne("addUser",addUserCase);   //实例化我的modul中的User类对象，并执行配置文件（SQLMapper.xml）中的sql语句（按id匹配）
        System.out.println(user.toString());  //打印出 user结果

        //判断返回结果是否满足期望
        Assert.assertEquals(addUserCase.getExpected(),result);
    }


    private String getResult(AddUserCase addUserCase) throws IOException {
        //下面的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.addUserUrl);  //实例化post请求，并传入请求url
        JSONObject param = new JSONObject();  //java代码封装为json字符串
        param.put("userName",addUserCase.getUserName()); //调用 addUserCase 类中的 getUserName()方法
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置请求头 header 信息
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");  //实例化请求体，并传入设置的参数
        post.setEntity(entity);  //调用 setEntit()方法，使post请求体生效
        //设置 cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);  //传入之前登陆接口获取的cookies信息
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post 方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);  // 使用 response 对象来接收
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);   //打印 结果

        return result;  //返回结果值
    }
}
