package com.spring.sleuth.demo.server1_sb2.services;

import com.spring.sleuth.demo.server1_sb2.config.custom.RestTemplateCustom;
import com.spring.sleuth.demo.server1_sb2.services.kafka.producers.ServerMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl {
    
    private final RestTemplateCustom restTemplateCustom;

    private final ServerMessagePublisher serverMessagePublisher;

    public void service2FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8081/api/service2/simple", Void.class);
        log.info("Message");
    }

    public void service3FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8082/api/service3/simple", Void.class);
        log.info("Message");
    }

    public void serviceKafkaFlowRequest() {
        log.info("Publish to Kafka...");
        serverMessagePublisher.publish("String Object");
    }
}
