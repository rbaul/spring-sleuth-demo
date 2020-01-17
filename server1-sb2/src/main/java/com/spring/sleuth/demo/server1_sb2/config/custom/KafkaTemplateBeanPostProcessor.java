package com.spring.sleuth.demo.server1_sb2.config.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;

@Slf4j
//@Component
public class KafkaTemplateBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof KafkaTemplate) {
            try {
                log.info("Kafka template configure properties update with interceptor");
                KafkaTemplate kafkaTemplate = (KafkaTemplate) bean;
                DefaultKafkaProducerFactory producerFactory = (DefaultKafkaProducerFactory) kafkaTemplate.getProducerFactory();
                Object producerInterceptorContent = producerFactory.getConfigurationProperties().get(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG);
                if (producerInterceptorContent instanceof ArrayList) {
                    ArrayList producerInterceptorContentList = (ArrayList) producerInterceptorContent;
                    producerInterceptorContentList.add(CustomProducerInterceptor.class);
                } else {
                    log.error("Missed config property {} in KafkaTemplate bean {}", ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, beanName);
                }


//                Map configs = ((DefaultKafkaProducerFactory) ((KafkaTemplate) bean).getProducerFactory()).configs;


//                Map<String, Object> producerConfigs = (Map<String, Object>)((DefaultKafkaProducerFactory) ((KafkaTemplate) bean).getProducerFactory()).getConfigurationProperties();
//                Map<String, Object> producerConfigs = new HashMap<>(((DefaultKafkaProducerFactory) ((KafkaTemplate) bean).getProducerFactory()).getConfigurationProperties());
////                Map<String, Object> consumerConfigs = (Map<String, Object>) bean;
////                Map<String, Object> producerConfigs = (Map<String, Object>) bean;
//                List<Class<?>> interceptorsList = (List<Class<?>>) producerConfigs.getOrDefault(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
//                interceptorsList.add(CustomProducerInterceptor.class);
//                producerConfigs.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
//
//                DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory(producerConfigs);

//                List<Class<?>> interceptorsList = (List<Class<?>>) consumerConfigs.getOrDefault(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
//                interceptorsList.add(CustomConsumerInterceptor.class);
//                consumerConfigs.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
            } catch (Exception e) {
                log.error("Failed add interceptor CustomConsumerInterceptor to consumer properties");
            }
        }
        return bean;
    }
}
