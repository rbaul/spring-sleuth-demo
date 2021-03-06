package com.spring.sleuth.demo.server3_sb1.config;

import com.spring.sleuth.demo.server3_sb1.services.Server1ClientImpl;
import com.spring.sleuth.demo.server3_sb1.services.Server2ClientImpl;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {Server1ClientImpl.class, Server2ClientImpl.class})
public class ServerConfig {

    @Bean
    public Sampler sampler() {
        return new AlwaysSampler();
    }
}
