package com.jirengu.kafka;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Resource
    private Producer producer;
    @GetMapping
    public String data(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "ok";
    }
}
