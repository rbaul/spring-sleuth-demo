package com.spring.sleuth.demo.server1_sb2.services.kafka.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServerMessagePublisher extends BasePublisher<Object> {

    public static final String $_KAFKA_TOPICS_SERVER_1_SOME_TOPIC = "${kafka.topics.server1.some-topic}";

    @Autowired
    public ServerMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate,
                                  @Value($_KAFKA_TOPICS_SERVER_1_SOME_TOPIC) String topic) {
        super(kafkaTemplate, topic);
    }
}
