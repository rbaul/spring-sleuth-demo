package com.spring.sleuth.demo.server1_sb2.config;

import com.spring.sleuth.demo.server1_sb2.services.Server2ClientImpl;
import com.spring.sleuth.demo.server1_sb2.services.Server3ClientImpl;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {Server2ClientImpl.class, Server3ClientImpl.class})
public class ServerConfig {
}
