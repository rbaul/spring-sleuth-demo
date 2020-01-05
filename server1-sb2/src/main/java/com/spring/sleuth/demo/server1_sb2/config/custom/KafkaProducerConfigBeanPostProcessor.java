package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class KafkaProducerConfigBeanPostProcessor implements BeanPostProcessor {

    public static final String PRODUCER_CONFIGS_BEAN_NAME = "producerConfigs";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, PRODUCER_CONFIGS_BEAN_NAME) && bean instanceof Map) {
            try {
                log.info("Kafka producer properties update with interceptor");
                Map<String, Object> producerConfigs = (Map<String, Object>) bean;
                List<Class<?>> interceptorsList = (List<Class<?>>) producerConfigs.getOrDefault(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
                interceptorsList.add(CustomProducerInterceptor.class);
                producerConfigs.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
            } catch (Exception e) {
                log.error("Failed add interceptor CustomProducerInterceptor to producer properties");
            }
        }
        return bean;
    }
}
