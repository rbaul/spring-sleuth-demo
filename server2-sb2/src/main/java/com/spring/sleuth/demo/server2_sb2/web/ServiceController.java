package com.spring.sleuth.demo.server2_sb2.web;

import com.spring.sleuth.demo.server2_sb2.services.AsyncServiceImpl;
import com.spring.sleuth.demo.server2_sb2.services.ServiceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Service 2 - Spring Boot 2")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ServiceController {

    private final ServiceServiceImpl service1Service;

    private final AsyncServiceImpl asyncService;

    @ApiOperation("Simple flow request - only service 2")
    @GetMapping("simple")
    public void simpleFlowRequest() {
        log.info("Simple flow request - only service 2");
    }

    @ApiOperation("Simple async flow request - only service 2")
    @GetMapping("simple/async")
    public void simpleAsyncFlowRequest() {
        log.info("Simple async flow request - only service 2");
        asyncService.asyncLocal();
    }

    @ApiOperation("Micro-Service REST flow request - REST to service 1 (Spring Boot 2)")
    @GetMapping("service1")
    public void service1FlowRequest() {
        log.info("Micro-Service REST flow request - REST to service 2 (Spring Boot 2)");
        service1Service.service1FlowRequest();
    }

    @ApiOperation("Micro-Service REST flow async request - REST to service 1 (Spring Boot 2)")
    @GetMapping("service1/async")
    public void service1AsyncFlowRequest() {
        log.info("Micro-Service REST flow async request - REST to service 1 (Spring Boot 2)");
        asyncService.asyncServer1();
    }

    @ApiOperation("Micro-Service REST flow request - REST to service 3 (Spring Boot 1)")
    @GetMapping("service3")
    public void service3FlowRequest() {
        log.info("Micro-Service REST flow request - REST to service 3 (Spring Boot 1)");
        service1Service.service3FlowRequest();
    }

    @ApiOperation("Micro-Service REST flow async request - REST to service 3 (Spring Boot 1)")
    @GetMapping("service3/async")
    public void service3AsyncFlowRequest() {
        log.info("Micro-Service REST flow async request - REST to service 3 (Spring Boot 1)");
        asyncService.asyncServer3();
    }

    @ApiOperation("Micro-Service Kafka flow to all services")
    @GetMapping("kafka")
    public void serviceKafkaFlowRequest() {
        log.info("Micro-Service Kafka flow to all services");
        service1Service.serviceKafkaFlowRequest();
    }

    @ApiOperation("Micro-Service Kafka async flow to all services")
    @GetMapping("kafka/async")
    public void serviceAsyncKafkaFlowRequest() {
        log.info("Micro-Service Kafka async flow to all services");
        asyncService.asyncKafka();
    }
}
