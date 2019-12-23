package com.spring.sleuth.demo.server1_sb2.config;

import com.spring.sleuth.demo.server1_sb2.config.custom.CustomRestTemplateCustomizer;
import com.spring.sleuth.demo.server1_sb2.config.custom.RestTemplateCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CustomRestTemplateConfig {

    /**
     * Will apply automatically to default RestTemplateBuilder (@RestTemplateAutoConfiguration)
     */
    @Bean
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }

    /**
     * @param restTemplateBuilder - default already include traceRestTemplateCustomizer
     */
    @Bean
    public RestTemplateCustom restTemplateCustom(RestTemplateBuilder restTemplateBuilder) {
        return new RestTemplateCustom(restTemplateBuilder.build());
    }

//    /**
//     * Manual customize RestTemplate traceRestTemplateCustomizer
//     */
//    @Bean
//    public RestTemplateCustom restTemplateCustom(RestTemplateCustomizer traceRestTemplateCustomizer) {
//        RestTemplate restTemplate = new RestTemplate();
//        traceRestTemplateCustomizer.customize(restTemplate);
//        return new RestTemplateCustom(restTemplate);
//    }
//
//
//    /**
//     * Manual insert traceRestTemplateCustomizer
//     */
//    @Bean
//    public RestTemplateCustom restTemplateCustom(RestTemplateBuilder restTemplateBuilder, RestTemplateCustomizer traceRestTemplateCustomizer) {
//        RestTemplate restTemplate = restTemplateBuilder.additionalCustomizers(traceRestTemplateCustomizer).build();
//        return new RestTemplateCustom(restTemplate);
//    }

}
