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

    private final Server2ClientImpl server2Client;

    private final Server3ClientImpl server3Client;

    private final ServerMessagePublisher serverMessagePublisher;

    public void service2FlowRequest() {
        log.info("Message to Server 2");
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8081/api/simple", Void.class);
        server2Client.simpleFlowRequest();
    }

    public void service3FlowRequest() {
        log.info("Message to Server 3");
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8082/api/simple", Void.class);
        server3Client.simpleFlowRequest();
    }

    public void serviceKafkaFlowRequest() {
        log.info("Publish to Kafka...");
        serverMessagePublisher.publish("String Object");
    }
}
