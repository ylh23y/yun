package com.yun;
 
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
http://192.168.188.171:9082/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
   
    @Bean
    public Docket testApi(){
    	Set<String> set = new HashSet<String>();
       Docket docket = new Docket(DocumentationType.SWAGGER_2)            
                .consumes(set)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yun.security.server.controller"))
				.paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
              
       return docket;
    }
   
    @SuppressWarnings("unchecked")
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .genericModelSubstitutes(DeferredResult.class)
                //.genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(Predicates.or(PathSelectors.regex("/com/.*")))//过滤的接口
                
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户系统")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }  
}