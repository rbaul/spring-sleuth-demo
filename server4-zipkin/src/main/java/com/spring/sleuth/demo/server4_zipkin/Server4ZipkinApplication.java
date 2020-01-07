package com.spring.sleuth.demo.server4_zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class Server4ZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(Server4ZipkinApplication.class, args);
    }

}
