package com.jirengu.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @KafkaListener(topics = "my-first-topic-2")
    public void consumeTopic(String msg) {
        logger.info("receive message : {}", msg);
    }
}
