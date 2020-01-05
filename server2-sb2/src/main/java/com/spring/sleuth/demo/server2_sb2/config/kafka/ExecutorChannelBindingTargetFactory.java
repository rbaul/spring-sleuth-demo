package com.spring.sleuth.demo.server2_sb2.config.kafka;

import org.springframework.cloud.stream.binding.AbstractBindingTargetFactory;
import org.springframework.cloud.stream.binding.CompositeMessageChannelConfigurer;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.stereotype.Service;

/**
 * Support ExecutorChannel
 */
@Primary
@Service
public class ExecutorChannelBindingTargetFactory extends AbstractBindingTargetFactory<ExecutorChannel> {

    private final CompositeMessageChannelConfigurer compositeMessageChannelConfigurer;

    public ExecutorChannelBindingTargetFactory(CompositeMessageChannelConfigurer compositeMessageChannelConfigurer) {
        super(ExecutorChannel.class);
        this.compositeMessageChannelConfigurer = compositeMessageChannelConfigurer;
    }

    @Override
    public ExecutorChannel createInput(String name) {
        ExecutorChannel executorChannel = new ExecutorChannel(Runnable::run);
        this.compositeMessageChannelConfigurer.configureInputChannel(executorChannel, name);
        return executorChannel;
    }

    @Override
    public ExecutorChannel createOutput(String name) {
        ExecutorChannel executorChannel = new ExecutorChannel(Runnable::run);
        this.compositeMessageChannelConfigurer.configureOutputChannel(executorChannel, name);
        return executorChannel;
    }
}