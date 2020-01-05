package com.spring.sleuth.demo.server1_sb2.config.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class NewJsonDeserializer<T> extends JsonDeserializer<T> {

    public NewJsonDeserializer() {
    }

    public NewJsonDeserializer(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public NewJsonDeserializer(Class<T> targetType) {
        super(targetType);
    }

    public NewJsonDeserializer(Class<T> targetType, ObjectMapper objectMapper) {
        super(targetType, objectMapper);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return super.deserialize(topic, data);
        } catch (SerializationException ex) {
            return null;
        }
    }
}