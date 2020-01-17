package com.spring.sleuth.demo.server1_sb2.config.custom;

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
        if (bean instanceof ConcurrentKafkaListenerContainerFactory) {
            log.info("sdaasdas");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ConcurrentKafkaListenerContainerFactory) {
            try {
//                ProxyFactory proxyFactory = new ProxyFactory(bean);
//                proxyFactory.addAdvice(interceptor());
//                proxyFactory.setProxyTargetClass(true);
//                return proxyFactory.getProxy();


                ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory = (ConcurrentKafkaListenerContainerFactory) bean;
                ConsumerFactory consumerFactory = concurrentKafkaListenerContainerFactory.getConsumerFactory();
                Object interceptorClasses = consumerFactory.getConfigurationProperties().get(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG);
                if (interceptorClasses instanceof ArrayList) {
                    ArrayList interceptorClassesList = (ArrayList) interceptorClasses;
                    interceptorClassesList.add(CustomConsumerInterceptor.class);
                } else {
                    log.error("Missed config property {} in KafkaListener bean {}", ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, beanName);
                }

////                concurrentKafkaListenerContainerFactory.setRecordInterceptor();
//                ConsumerFactory consumerFactory = concurrentKafkaListenerContainerFactory.getConsumerFactory();
////                consumerFactory.
//                Map<String, Object> consumerConfigs = new HashMap<>(consumerFactory.getConfigurationProperties());
////                new DefaultKafkaConsumerFactory<>(consumerConfigs,
////                        new StringDeserializer(), new NewJsonDeserializer<>(targetType));
////                consumerFactory.getConfigurationProperties()
////                concurrentKafkaListenerContainerFactory.setConsumerFactory();
//
//
//                log.info("Kafka consumer properties update with interceptor");
////                Map<String, Object> consumerConfigs = (Map<String, Object>) bean;
//                List<Class<?>> interceptorsList = (List<Class<?>>) consumerConfigs.getOrDefault(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
//                interceptorsList.add(CustomConsumerInterceptor.class);
//                consumerConfigs.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
            } catch (Exception e) {
                log.error("Failed add interceptor CustomConsumerInterceptor to consumer properties");
            }
        }
        return bean;
    }

//    private MethodInterceptor interceptor() {
////        ((ConcurrentKafkaListenerContainerFactory) bean).getConsumerFactory().getConfigurationProperties()
//        return invocation -> {
//            if (invocation.getMethod().equals("getConsumerFactory")) {
//                return invocation.proceed();
//            } else {
//                return invocation.proceed();
//            }
//        };
//    }
}
