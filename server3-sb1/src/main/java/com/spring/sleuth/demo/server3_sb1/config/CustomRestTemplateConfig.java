package com.spring.sleuth.demo.server3_sb1.config;

import com.spring.sleuth.demo.server3_sb1.config.custom.CustomRestTemplateCustomizer;
import com.spring.sleuth.demo.server3_sb1.config.custom.RestTemplateCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Bean
    public RestTemplateCustomizer traceRestTemplateCustomizer(TraceRestTemplateInterceptor traceRestTemplateInterceptor) {
        return restTemplate -> {
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(
                    restTemplate.getInterceptors());
            interceptors.add(traceRestTemplateInterceptor);
            restTemplate.setInterceptors(interceptors);
        };
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
