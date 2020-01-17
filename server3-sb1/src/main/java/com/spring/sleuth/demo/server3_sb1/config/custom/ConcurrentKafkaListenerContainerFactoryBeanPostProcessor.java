package com.spring.sleuth.demo.server3_sb1.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
//@Component
public class ConcurrentKafkaListenerContainerFactoryBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ConcurrentKafkaListenerContainerFactory) {
            log.info("ConcurrentKafkaListenerContainerFactory '{}' added interceptor '{}' to config", bean, CustomConsumerInterceptor.class);
            ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory = (ConcurrentKafkaListenerContainerFactory) bean;
            ConsumerFactory consumerFactory = concurrentKafkaListenerContainerFactory.getConsumerFactory();
            Object interceptorClasses = consumerFactory.getConfigurationProperties().get(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG);
            if (interceptorClasses instanceof ArrayList) {
                ArrayList interceptorClassesList = (ArrayList) interceptorClasses;
                interceptorClassesList.add(CustomConsumerInterceptor.class);
            } else {
                log.error("Missed config property {} in KafkaListener bean {}", ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, beanName);
            }
        }
        return bean;
    }

}
