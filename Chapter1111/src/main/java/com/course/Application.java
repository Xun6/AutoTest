package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * 这是一个启动类，第二种方法如下（不用再去扫描其他包）
 * */
@EnableScheduling
@SpringBootApplication
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args){
        Application.context = SpringApplication.run(Application.class,args);
    }

    //预摧毁方法
    @PreDestroy
    public void close(){
        Application.context.close();

    }
}
