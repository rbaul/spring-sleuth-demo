package com.spring.sleuth.demo.server3_sb1.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        // customize RestTemplate
        log.info("Custom RestTemplate Customizer");
    }
}
