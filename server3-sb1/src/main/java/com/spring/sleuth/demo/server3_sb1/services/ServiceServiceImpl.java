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

    private final Server1ClientImpl server1Client;

    private final Server2ClientImpl server2Client;

    public void service1FlowRequest() {
        log.info("Message to Server 1");
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8080/api/simple", Void.class);
        server1Client.simpleFlowRequest();
    }

    public void service2FlowRequest() {
        log.info("Message to Server 2");
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8081/api/simple", Void.class);
        server2Client.simpleFlowRequest();
    }

    public void serviceKafkaFlowRequest() {
        log.info("Publish to Kafka...");
        serverMessagePublisher.publish("String Object (Server 3 - Spring Boot 1)");
    }
}
