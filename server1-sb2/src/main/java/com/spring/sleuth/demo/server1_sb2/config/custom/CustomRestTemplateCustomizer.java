package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(RestTemplate restTemplate) {
        // customize RestTemplate
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(
                restTemplate.getInterceptors());
        interceptors.add(0, (request, body, execution) -> {
            log.info("interceptor");
            return execution.execute(request, body);
        });
        restTemplate.setInterceptors(interceptors);
        log.info("Custom RestTemplate Customizer");
    }
}
