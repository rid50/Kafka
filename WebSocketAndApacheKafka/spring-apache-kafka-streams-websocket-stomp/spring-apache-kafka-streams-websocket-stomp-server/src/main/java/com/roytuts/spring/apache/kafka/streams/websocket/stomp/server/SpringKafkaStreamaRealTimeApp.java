package com.roytuts.spring.apache.kafka.streams.websocket.stomp.server;

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

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStreamaRealTimeApp.class, args);
	}
	
	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;
	
    @Bean
    public ApplicationRunner runner() {
    //public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
			System.out.println("Greeting Message :: ****************************************************************************");			
            //template.send(kafkaInputTopic, "Hello, Kafka!");
        };
    }		

}
