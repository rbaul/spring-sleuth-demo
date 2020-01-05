package com.spring.sleuth.demo.server3_sb1.services;

import com.spring.sleuth.demo.server3_sb1.config.custom.RestTemplateCustom;
import com.spring.sleuth.demo.server3_sb1.services.kafka.producers.ServerMessagePublisher;
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
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8080/api/service1/simple", Void.class);
        log.info("Message");
    }

    public void service3FlowRequest() {

    }

    public void serviceKafkaFlowRequest() {
        log.info("Publish to Kafka...");
        serverMessagePublisher.publish("String Object (Server 3 - Spring Boot 1)");
    }
}
