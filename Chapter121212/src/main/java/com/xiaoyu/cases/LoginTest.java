package com.xiaoyu.cases;    //这是我的测试用例包

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.InterfaceName;
import com.xiaoyu.utils.ConfigFile;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.BeforeTest;

//登录测试
public class LoginTest {

    @BeforeTest(groups = "Login",description = "这是测试准备工作")
    public void beforTest(){
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);  //调用拼接的 url 地址,赋值给配置类（TestConfig）中设置的变量
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();   //实例化 defaultHttpClient

    }
}
