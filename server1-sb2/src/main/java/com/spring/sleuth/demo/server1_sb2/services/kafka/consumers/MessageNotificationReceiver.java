package com.spring.sleuth.demo.server1_sb2.services.kafka.consumers;

import com.spring.sleuth.demo.server1_sb2.config.KafkaConfig;
import com.spring.sleuth.demo.server1_sb2.services.kafka.producers.ServerMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageNotificationReceiver {

    @KafkaListener(containerFactory = KafkaConfig.SOME_MESSAGE_LISTENER, topics = ServerMessagePublisher.$_KAFKA_TOPICS_SOME_TOPIC)
    public void receive(@Payload Object data, @Headers MessageHeaders messageHeaders) {
        log.info("Message received from KAFKA topic '{}': data '{}', headers '{}'", ServerMessagePublisher.$_KAFKA_TOPICS_SOME_TOPIC, data, messageHeaders);
    }

}
