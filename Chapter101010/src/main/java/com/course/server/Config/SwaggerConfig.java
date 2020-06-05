package com.course.server.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 这是 封装的 swagger配置类
 * 需要先引入依赖包
 * */
@Configuration    //表示专门加载配置文件
@EnableSwagger2    //引入依赖的注解
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))          //正则匹配访问方法的路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("小鱼的接口文档")
                .contact(new Contact("xiaoyu","","13617920171@163.com"))
                .description("这是我的swagger生成的接口文档")
                .version("1.0.0.0")
                .build();

    }
}
