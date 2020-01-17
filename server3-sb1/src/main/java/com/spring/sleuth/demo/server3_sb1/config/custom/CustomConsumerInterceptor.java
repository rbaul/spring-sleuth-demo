package com.spring.sleuth.demo.server3_sb1.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.slf4j.MDC;

import java.util.Map;

@Slf4j
public class CustomConsumerInterceptor<K, V> implements ConsumerInterceptor<K, V> {

    @Override
    public ConsumerRecords<K, V> onConsume(ConsumerRecords<K, V> records) {
        Headers headers = records.iterator().next().headers();
        Header customId = headers.lastHeader("customId");
        if (customId != null) {
            String arg = new String(customId.value());
            log.info("Custom ID {}", arg);
            MDC.put("customId", arg);
        }

        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        log.info("onCommit");
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
