package com.spring.sleuth.demo.server1_sb2.services;

import com.spring.sleuth.demo.server1_sb2.config.custom.RestTemplateCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Service1ServiceImpl {
    
    private final RestTemplateCustom restTemplateCustom;

    public void service2FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8081/api/service1/simple", Void.class);
        log.info("Message");
    }

    public void service3FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8082/api/service1/simple/async", Void.class);
        log.info("Message");
    }

    public void serviceKafkaFlowRequest() {
    }
}
