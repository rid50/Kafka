package com.kafka.kafkademo.controller;

import com.kafka.kafkademo.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    MessageProducer messageProducer;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message){
        messageProducer.sendMessage("ytube-topic", message);
        return "Message has been sent successfully "+message;
    }
}
