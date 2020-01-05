package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.RecordInterceptor;

/**
 * Additional way to add interceptor on receive (Spring Boot 2 only)
 */
@Slf4j
public class CustomRecordInterceptor<K, V> implements RecordInterceptor<K, V> {
    @Override
    public ConsumerRecord<K, V> intercept(ConsumerRecord<K, V> record) {
        log.info("Custom Record Interceptor execution");
        return record;
    }
}
