package com.spring.sleuth.demo.server1_sb2.services.kafka.producers;

public interface Publisher<T> {
    void publish(T objDto);
}