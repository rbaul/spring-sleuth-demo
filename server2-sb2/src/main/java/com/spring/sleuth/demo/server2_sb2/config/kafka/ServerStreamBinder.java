package com.spring.sleuth.demo.server2_sb2.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public interface ServerStreamBinder {

    String SOME_TOPIC = "server2-some-topic";

    @Input(ServerStreamBinder.SOME_TOPIC)
    ExecutorChannel someTopicInput();

    @Output(ServerStreamBinder.SOME_TOPIC)
    ExecutorChannel someTopicOutput();

    default <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}