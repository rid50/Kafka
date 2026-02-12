package org.yaruss.kafka.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.ApplicationRunner;
//import com.roytuts.spring.apache.kafka.streams.websocket.stomp.server.config.WebSocketConfig;

@SpringBootApplication
//@SpringBootApplication(exclude = {WebSocketConfig.class})
public class SpringKafkaStreamaRealTimeApp {
    // ANSI escape code constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStreamaRealTimeApp.class, args);
	}
	
	// @Value("${kafka.input.topic}")
	// private String kafkaInputTopic;
	
    @Bean
    public ApplicationRunner runner() {
    //public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
		System.out.println(GREEN + "ApplicationRunner: ****************************************************************" + RESET);			
            //template.send(kafkaInputTopic, "Hello, Kafka!");
        };
    }		

}
