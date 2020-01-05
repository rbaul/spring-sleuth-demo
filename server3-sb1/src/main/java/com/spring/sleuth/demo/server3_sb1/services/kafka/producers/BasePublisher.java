package com.spring.sleuth.demo.server3_sb1.services.kafka.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class BasePublisher<T> implements Publisher<T> {

    private KafkaTemplate<String, T> kafkaTemplate;

    private String topic;

    public BasePublisher(KafkaTemplate<String, T> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(T objDto) {
        ListenableFuture<SendResult<String, T>> future = kafkaTemplate.send(topic, objDto);
        future.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("On notifier process, failed to publish to kafka: '{}'", objDto, ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("Object was published to topic {}\n object = {}, partition = {}, offset = {}",
                        topic, objDto, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }

}