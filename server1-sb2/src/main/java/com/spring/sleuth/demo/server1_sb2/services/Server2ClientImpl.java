package com.spring.sleuth.demo.server1_sb2.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "server2-client", url = "http://localhost:8081", path = "/api")
public interface Server2ClientImpl {

    @GetMapping("simple")
    void simpleFlowRequest();

    @GetMapping("simple/async")
    void simpleAsyncFlowRequest();
}