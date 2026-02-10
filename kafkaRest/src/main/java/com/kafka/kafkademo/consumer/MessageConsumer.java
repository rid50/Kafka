package com.kafka.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "ytube-topic", groupId = "ytube-group")
    public void consumerMessage(String message){
        System.out.println("Received Message "+ message);
    }
}
