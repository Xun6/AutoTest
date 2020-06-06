package com.xiaoyu.cases;

import com.xiaoyu.Config.TestConfig;
import com.xiaoyu.modul.GetUserListCase;
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
import java.util.List;

public class GetUserInfoListTest {


    @Test(dependsOnGroups = "Login",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        SqlSession session = DataBaseUtil.getSqlSession();  //定义、调用我的sql语句执行方法类
        GetUserListCase getUserListCase = session.selectOne("getUserListCase1",1);  //实例化我的modul中的 GetUserListCase 类对象，并执行配置文件（SQLMapper.xml）中的sql语句（按id匹配）
        System.out.println(getUserListCase.toString()); //打印 getUserListCase 的结果
        System.out.println(TestConfig.getUserListUrl);  //打印 接口地址（url）
        
//        //下边是写完接口的代码
//        JSONArray resultJson = getJsonResult(getUserListCase);
//        /**
//         * 可以先讲
//         * */
//        Thread.sleep(2000);
//        List<UserCase> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
//        for(UserCase user : userList){
//            System.out.println("list获取的 user："+user.toString());
//        }
//        JSONArray userListJson = new JSONArray(userList);  //??
//        //判断俩值字符串长度是否相等
//        Assert.assertEquals(userListJson.length(),resultJson.length());
//        for(int i =0;i<resultJson.length();i++){
//            JSONObject expect = (JSONObject) resultJson.get(i);
//            JSONObject actual = (JSONObject) userListJson.get(i);
//            Assert.assertEquals(expect.toString(),actual.toString());
//        }
    }



//    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
//        HttpPost post = new HttpPost(TestConfig.getUserListUrl);    //实例化post请求，并传入请求url
//        JSONObject param = new JSONObject();
//        param.put("userName",getUserListCase.getUserName());    //调用 getUserListCase 类中的 getUserName()方法,
//        param.put("sex",getUserListCase.getSex());
//        param.put("age",getUserListCase.getAge());
//        //设置请求头信息，设置header
//        post.setHeader("content-type","application/json");
//        //将参数信息添加到方法中
//        StringEntity entity = new StringEntity(param.toString(),"utf-8");  //实例化请求体，并传入设置的参数
//        post.setEntity(entity); //调用 setEntity()方法，使post请求体生效
//        //设置cookies
//        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);  //将之前获取的cookie信息 TestConfig.store 传入
//        //声明一个对象来进行响应结果的存储
//        String result;
//        //执行 post方法，并使用 HttpResponse 对象引用来接收
//        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
//        result = EntityUtils.toString(response.getEntity(),"utf-8");
//        JSONArray jsonArray = new JSONArray(result);  //??
//
//        System.out.println("调用接口list result:"+result);
//
//        return jsonArray;
//    }
}
