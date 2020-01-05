package com.spring.sleuth.demo.server1_sb2.config.custom;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;

import java.util.Map;
import java.util.UUID;

public class CustomProducerInterceptor<K, V> implements ProducerInterceptor<K, V> {
    @Override
    public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {
        Headers headers = record.headers();
        headers.add("customId", UUID.randomUUID().toString().getBytes());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
