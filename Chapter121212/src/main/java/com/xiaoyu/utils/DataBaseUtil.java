package com.xiaoyu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * 这是一个工具类，封装了执行sql语句的方法
 * */
public class DataBaseUtil {
    //用于执行sql语句的一个方法 SqlSession
    public static SqlSession getSqlSession() throws IOException {
        //获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");

        //加载配置的资源文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

        //sqlSession用来执行配置文件中的sql语句
        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }
}
