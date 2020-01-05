package com.spring.sleuth.demo.server1_sb2.config;

import com.spring.sleuth.demo.server1_sb2.config.custom.NewJsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import no.sysco.middleware.kafka.interceptor.zipkin.TracingConsumerInterceptor;
import no.sysco.middleware.kafka.interceptor.zipkin.TracingProducerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig extends KafkaBaseConfig {

    public static final String SOME_MESSAGE_LISTENER = "some-message";

    private final String defaultGroupName;

    @Autowired
    private KafkaProperties kafkaProperties;

    public KafkaConfig(@Value("${spring.application.name}") String appName) {
        super(appName);
        defaultGroupName = appName;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Default
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        List<Class<?>> interceptorsList = (List<Class<?>>) props.getOrDefault(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
        interceptorsList.add(TracingProducerInterceptor.class);
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
        return props;
    }

    // If you only need one kind of deserialization, you only need to set the
    // Consumer configuration properties. Uncomment this and remove all others below.
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, NewJsonDeserializer.class);
        List<Class<?>> interceptorsList = (List<Class<?>>) props.getOrDefault(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, new ArrayList<>());
        interceptorsList.add(TracingConsumerInterceptor.class);
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorsList);
        return props;
    }

    @Bean
    public KafkaTemplate<String, Object> someTopicKafkaTemplate() {
        return createProducerTemplate();
    }

    @Bean(name = SOME_MESSAGE_LISTENER)
    public ConcurrentKafkaListenerContainerFactory<String, Object> someMessageContainerFactory() {
        return createKafkaListenerContainer(Object.class, defaultGroupName);
    }

}
