package com.spring.sleuth.demo.server2_sb2.services;

import com.spring.sleuth.demo.server2_sb2.config.custom.RestTemplateCustom;
import com.spring.sleuth.demo.server2_sb2.config.kafka.ServerStreamBinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl {

    private final ServerStreamBinder serverStreamBinder;

    private final RestTemplateCustom restTemplateCustom;

    public void service2FlowRequest() {
        restTemplateCustom.getRestTemplate().getForEntity("http://localhost:8080/api/service1/simple", Void.class);
        log.info("Message");
    }

    public void service3FlowRequest() {

    }

    public void serviceKafkaFlowRequest() {
        log.info("Publish to Kafka...");
        Message<String> message = MessageBuilder.withPayload("String Object").build();
        serverStreamBinder.someTopicOutput().send(message);
    }
}
