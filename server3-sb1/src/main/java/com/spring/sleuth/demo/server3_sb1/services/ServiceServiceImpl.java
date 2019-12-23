package com.spring.sleuth.demo.server3_sb1.services;

import com.spring.sleuth.demo.server3_sb1.config.custom.RestTemplateCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl {

    private final RestTemplateCustom restTemplateCustom;

    public void service2FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8081/api/service1/simple", Void.class);
        log.info("Message");
    }

    public void service3FlowRequest() {

    }

    public void serviceKafkaFlowRequest() {
    }
}
