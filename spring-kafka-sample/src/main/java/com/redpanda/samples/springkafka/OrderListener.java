package com.redpanda.samples.springkafka;

import com.redpanda.samples.springkafka.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.FixedDelayStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000),
            fixedDelayTopicStrategy = FixedDelayStrategy.SINGLE_TOPIC)
    @KafkaListener(topics = "orders")
    public void listen(Order order) {
        log.info(">>> Order received: " + order.getId() + " Amount: " + order.getAmount());
        //Validate the payload and throw any errors if necessary
        if(order.getId() == 1 ) {
            throw new RuntimeException("Order validation failed: Unable to serve orders with the id 1");
        }
    }

}
