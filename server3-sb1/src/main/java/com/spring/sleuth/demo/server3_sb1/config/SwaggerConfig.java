package com.spring.sleuth.demo.server3_sb1.config;

import com.spring.sleuth.demo.server3_sb1.web.ServiceController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${application.title:Service 3 (Spring Boot 1)}")
    private String title;

    @Bean
    public Docket getSecureDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Service 3")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ServiceController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .build();
    }


}