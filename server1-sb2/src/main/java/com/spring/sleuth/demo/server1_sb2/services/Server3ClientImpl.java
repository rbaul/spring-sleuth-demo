package com.spring.sleuth.demo.server1_sb2.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "server3-client", url = "http://localhost:8082")
@RequestMapping("/api/service3")
public interface Server3ClientImpl {

    @GetMapping("simple")
    void simpleFlowRequest();

    @GetMapping("simple/async")
    void simpleAsyncFlowRequest();
}