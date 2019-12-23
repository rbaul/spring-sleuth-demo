package com.spring.sleuth.demo.server1_sb2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl {

    private final Service1ServiceImpl service1Service;

    @Async
    public void asyncLocal() {
        log.info("Async local");
    }

    @Async
    public void asyncServer2() {
        log.info("Async server 2");
        service1Service.service2FlowRequest();
    }

    @Async
    public void asyncServer3() {
        log.info("Async server 3");
        service1Service.service3FlowRequest();
    }

    @Async
    public void asyncKafka() {
        log.info("Async kafka");
        service1Service.serviceKafkaFlowRequest();
    }
}
