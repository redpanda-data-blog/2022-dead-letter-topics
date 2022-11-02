package com.redpanda.samples.springkafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

@EnableKafka
@Configuration
public class KafkaConfig {

    private KafkaTemplate<Object, Object> kafkaTemplate;

    public KafkaConfig(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate));
    }

}
