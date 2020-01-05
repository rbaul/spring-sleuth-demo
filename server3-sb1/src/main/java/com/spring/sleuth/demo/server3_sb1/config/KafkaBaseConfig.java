package com.spring.sleuth.demo.server3_sb1.config;

import com.spring.sleuth.demo.server3_sb1.config.custom.NewJsonDeserializer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public abstract class KafkaBaseConfig {

    private static final int DEFAULT_CONCURRENCY = 1;

    private String appName;
    private int defaultConcurrency;

    public KafkaBaseConfig(String appName) {
        this(appName, DEFAULT_CONCURRENCY);
    }

    public abstract Map<String, Object> producerConfigs();

    public abstract Map<String, Object> consumerConfigs();


    protected Map<String, Object> consumerConfig(String groupName) {
        Map<String, Object> consumerConfig = new HashMap<>(consumerConfigs());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, appName + "#" + groupName);
        consumerConfig.put(ConsumerConfig.CLIENT_ID_CONFIG, groupName);
        return consumerConfig;
    }

    protected Map<String, Object> producerConfig() {
        Map<String, Object> producerConfig = new HashMap<>(producerConfigs());
        return producerConfig;
    }

    protected <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    protected <T> KafkaTemplate<String, T> createProducerTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    protected <T> ConsumerFactory<String, T> consumerFactory(Class<T> targetType, String groupName) {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(groupName),
                new StringDeserializer(), new NewJsonDeserializer<>(targetType));
    }

    protected <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainer(Class<T> targetType, String groupName) {
        return createKafkaListenerContainer(targetType, groupName, defaultConcurrency);
    }

    protected <T> ConcurrentKafkaListenerContainerFactory<String, T> createKafkaListenerContainer(Class<T> targetType, String groupName, int concurrency) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(targetType, groupName));
        factory.setConcurrency(concurrency);
        return factory;
    }
}