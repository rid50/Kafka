package com.kafka.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class KafkademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
	}
	
    @Bean
    public NewTopic topicIn() {
        return TopicBuilder.name("ytube-topic")
                .partitions(10)
                .replicas(1)
                .build();
    }	

}
