package com.spring.sleuth.demo.server2_sb2.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListeners {

    @StreamListener(ServerStreamBinder.SOME_TOPIC)
    public void someTopicReceiver(@Payload Object data, @Headers MessageHeaders messageHeaders) {
        log.info("Message received from KAFKA topic: data '{}', headers '{}'", data, messageHeaders);
    }

}
