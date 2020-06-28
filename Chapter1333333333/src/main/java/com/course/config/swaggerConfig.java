package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
swagger配置(需要先引入依赖库)
*/
@Configuration
@EnableSwagger2
public class swaggerConfig {

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
                .contact(new Contact("小余","","13617920171@163.com"))  /*联系方式*/
                .description("这是我的用户管理Api")
                .version("1.0.2")
                .build();

    }
}
