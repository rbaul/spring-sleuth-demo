package com.spring.sleuth.demo.server2_sb2.config.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(ServerStreamBinder.class)
public class KafkaBinderConfig {

}
