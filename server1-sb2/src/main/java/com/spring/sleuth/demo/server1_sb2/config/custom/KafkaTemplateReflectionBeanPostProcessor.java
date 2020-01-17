package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class KafkaTemplateReflectionBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof KafkaTemplate) {
            log.info("Kafka template '{}' add interceptor '{}' to config", bean, CustomProducerInterceptor.class);
            KafkaTemplate kafkaTemplate = (KafkaTemplate) bean;
            try {

                Field producerFactoryField = KafkaTemplate.class.getDeclaredField("producerFactory");
                boolean producerFactoryFieldAccessible = producerFactoryField.isAccessible();

                producerFactoryField.setAccessible(true);

                Object producerFactoryObject = producerFactoryField.get(kafkaTemplate);

                Field configsField = DefaultKafkaProducerFactory.class.getDeclaredField("configs");
                boolean configsFieldAccessible = producerFactoryField.isAccessible();
                configsField.setAccessible(true);
                Map<String, Object> producerConfigs = new HashMap<String, Object>((Map<String, Object>) configsField.get(producerFactoryObject));
                List<Class<?>> interceptorsList = (List<Class<?>>) producerConfigs.getOrDefault(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
                interceptorsList.add(CustomProducerInterceptor.class);
                producerConfigs.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
                configsField.set(producerFactoryObject, producerConfigs);
                configsField.setAccessible(configsFieldAccessible);

                producerFactoryField.setAccessible(producerFactoryFieldAccessible);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed add config property {} in KafkaTemplate bean {}", ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, beanName, e);
            }

        }
        return bean;
    }
}
