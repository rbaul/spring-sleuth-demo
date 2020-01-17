package com.spring.sleuth.demo.server3_sb1.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ConcurrentKafkaListenerContainerFactoryReflectionBeanPostProcessor implements BeanPostProcessor {

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
            try {
                Field consumerConfigsField = DefaultKafkaConsumerFactory.class.getDeclaredField("configs");
                boolean consumerConfigsFieldAccessible = consumerConfigsField.isAccessible();

                consumerConfigsField.setAccessible(true);
                Map<String, Object> consumerConfigs = new HashMap<>(consumerFactory.getConfigurationProperties());

                List<Class<?>> interceptorsList = (List<Class<?>>) consumerConfigs.getOrDefault(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
                interceptorsList.add(CustomConsumerInterceptor.class);
                consumerConfigs.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
                consumerConfigsField.set(consumerFactory, consumerConfigs);

                consumerConfigsField.setAccessible(consumerConfigsFieldAccessible);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed add config property {} in KafkaListener bean {}", ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, beanName, e);
            }
        }
        return bean;
    }

}
