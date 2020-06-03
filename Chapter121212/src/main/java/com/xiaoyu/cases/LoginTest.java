package com.xiaoyu.cases;    //这是我的测试用例包

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.InterfaceName;
import com.xiaoyu.modul.LoginCase;
import com.xiaoyu.utils.ConfigFile;
import com.xiaoyu.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static sun.plugin2.main.server.LiveConnectSupport.getResult;

//登录测试
public class LoginTest {

    //使用组依赖（groups），适用于跨文件处理测试用例
    @BeforeTest(groups = "Login",description = "这是测试前准备工作")
    public void beforTest(){
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);  //调用拼接的 url 地址,赋值给配置类（TestConfig）中设置的变量
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();   //实例化 defaultHttpClient，方便后面调用

    }


    @Test(groups = "Login",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();  //定义、调用我的sql语句执行方法类
        LoginCase loginCase = session.selectOne("loginCase1",1);  //实例化我的modul中的类对象，并执行配置文件（SQLMapper.xml）中的sql语句（按id匹配）
        System.out.println(loginCase.toString());   //输出loginCase的结果
        System.out.println(TestConfig.loginUrl);  //输出接口地址

        //对返回结果进行测试，判断结果是否符合预期
        String result = getResult(loginCase);
        Assert.assertEquals(loginCase.getExpected(),result);
    }


    @Test(description = "用户登录失败接口")
    public void loginFalse() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase1",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);  //打印 拼接的接口url

        //对返回结果进行测试，判断结果是否符合预期
        String result = getResult(loginCase);    //定义结果变量（result），调用下面创建的 getResult()方法，且传参需满足 loginCase 类中的字段
        Assert.assertEquals(loginCase.getExpected(),result);
    }


    private String getResult(LoginCase loginCase) throws IOException {
        //下面的代码是写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);  //post请求接口url
        JSONObject param = new JSONObject();   //将java参数代码封装为json字符串
        param.put("userName",loginCase.getUserName());  //设置输入参数，与我的 loginCase 类中封装参数一致，通过特定方法 getUserName()调用
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);  //调用TestConfig中定义好的 defaultHttpClient 对象
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookies信息
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;


    }
}
