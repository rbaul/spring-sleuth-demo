package com.spring.sleuth.demo.server3_sb1.config.custom;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignCustomRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.info("Feign Custom Request Interceptor");
    }
}