package com.jirengu.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    private KafkaTemplate<String, String> kafka;
    public void sendMessage(String message) {
        kafka.send("my-first-topic-2", message);
    }
}
