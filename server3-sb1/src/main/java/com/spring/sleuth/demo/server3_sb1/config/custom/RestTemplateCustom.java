package com.spring.sleuth.demo.server3_sb1.config.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Getter
@RequiredArgsConstructor
public class RestTemplateCustom {
    private final RestTemplate restTemplate;
}
