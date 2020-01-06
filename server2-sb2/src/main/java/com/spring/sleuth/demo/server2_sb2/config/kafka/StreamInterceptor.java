package com.spring.sleuth.demo.server2_sb2.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ExecutorChannelInterceptor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// received method called only on PollableChannels
@Component
@Slf4j
@GlobalChannelInterceptor
public class StreamInterceptor implements ExecutorChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Intercept: add new header");
        MessageHeaderAccessor headers = MessageHeaderAccessor
                .getMutableAccessor(message);
        Map<String, Object> additionalHeaders = new HashMap<>();
        additionalHeaders.put("customId", UUID.randomUUID());

        headers.copyHeaders(additionalHeaders);
        return new GenericMessage<>(message.getPayload(), new MessageHeaders(headers.getMessageHeaders()));

//        message.getHeaders().put("customID", UUID.randomUUID().toString());
//        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("postSend");
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        log.info("afterSendCompletion");
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.info("preReceive");
        return true;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.info("postReceive");
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        log.info("afterReceiveCompletion");
    }

    @Override
    public Message<?> beforeHandle(Message<?> message, MessageChannel channel, MessageHandler handler) {
        log.info("beforeHandle");
        return message;
    }

    @Override
    public void afterMessageHandled(Message<?> message, MessageChannel channel, MessageHandler handler, Exception ex) {
        log.info("afterMessageHandled");
    }
}
