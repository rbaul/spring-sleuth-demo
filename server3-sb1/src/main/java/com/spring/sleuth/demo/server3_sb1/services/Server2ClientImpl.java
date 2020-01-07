package com.spring.sleuth.demo.server3_sb1.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "server2-client", url = "http://localhost:8081")
@RequestMapping("/api")
public interface Server2ClientImpl {

    @GetMapping("simple")
    void simpleFlowRequest();

    @GetMapping("simple/async")
    void simpleAsyncFlowRequest();
}