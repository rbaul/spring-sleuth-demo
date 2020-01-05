package com.spring.sleuth.demo.server3_sb1.services.kafka.producers;

public interface Publisher<T> {
    void publish(T objDto);
}