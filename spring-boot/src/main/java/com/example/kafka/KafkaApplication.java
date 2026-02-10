package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;


@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
	
    @Bean
    public NewTopic topicIn() {
        return TopicBuilder.name("streams-plaintext-input")
                .partitions(10)
                .replicas(1)
                .build();
    }
	
    @Bean
    public NewTopic topicOut() {
        return TopicBuilder.name("streams-pipe-output")
                .partitions(10)
                .replicas(1)
                .build();
    }	
 
    @KafkaListener(id = "exampleId", topics = "streams-plaintext-input")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    // ANSI escape code constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
	
    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
    //public ApplicationRunner runner() {
        return args -> {
			System.out.println(RED + "****************************************************************" + RESET);				
            template.send("streams-pipe-output", "Hello, Kafka!");
        };
    }	

}
