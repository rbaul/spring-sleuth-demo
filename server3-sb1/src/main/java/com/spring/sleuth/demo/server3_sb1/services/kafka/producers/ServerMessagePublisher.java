package com.spring.sleuth.demo.server3_sb1.services.kafka.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServerMessagePublisher extends BasePublisher<Object> {

    public static final String $_KAFKA_TOPICS_SERVER_3_SOME_TOPIC = "${kafka.topics.server3.some-topic}";

    @Autowired
    public ServerMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate,
                                  @Value($_KAFKA_TOPICS_SERVER_3_SOME_TOPIC) String topic) {
        super(kafkaTemplate, topic);
    }
}
