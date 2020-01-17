package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
//@Component
public class KafkaConsumerConfigBeanPostProcessor implements BeanPostProcessor {

    public static final String CONSUMER_CONFIGS_BEAN_NAME = "consumerConfigs";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, CONSUMER_CONFIGS_BEAN_NAME) && bean instanceof Map) {
            try {
                log.info("Kafka consumer properties update with interceptor");
                Map<String, Object> consumerConfigs = (Map<String, Object>) bean;
                List<Class<?>> interceptorsList = (List<Class<?>>) consumerConfigs.getOrDefault(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
                interceptorsList.add(CustomConsumerInterceptor.class);
                consumerConfigs.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
            } catch (Exception e) {
                log.error("Failed add interceptor CustomConsumerInterceptor to consumer properties");
            }
        }
        return bean;
    }
}
