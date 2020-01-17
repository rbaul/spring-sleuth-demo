package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;

import java.util.Map;
import java.util.UUID;

@Slf4j
public class CustomProducerInterceptor<K, V> implements ProducerInterceptor<K, V> {
    @Override
    public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {
        Headers headers = record.headers();
        UUID uuid = UUID.randomUUID();
        log.info("Added to header, customId '{}'", uuid);
        headers.add("customId", uuid.toString().getBytes());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("onAcknowledgement");
    }

    @Override
    public void close() {
        log.info("close");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("configure");
    }
}
