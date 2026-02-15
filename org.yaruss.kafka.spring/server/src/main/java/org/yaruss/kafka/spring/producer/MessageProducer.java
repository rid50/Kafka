package org.yaruss.kafka.spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import org.yaruss.kafka.spring.service.GreetingService;

@Component
//@Service
@EnableScheduling
public class MessageProducer {
    // ANSI escape code constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";	
	
	private boolean isEnabled = false;
	
	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	@Autowired
	private GreetingService greetingService;

	// @Autowired
	// private KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
	
	public void enableSheduling() {
		isEnabled = true;
	}

	public void disableSheduling() {
		isEnabled = false;
	}
	
	//@Bean
	@Scheduled(fixedRate = 1000)
	//@PostConstruct
	//public void produce(KafkaTemplate<String, String> kafkaTemplate) {	
	public void produce() {
		if (isEnabled) {
			System.out.println(RED + "Producer: ****************************************************************" + RESET);		
			String msg = greetingService.greet();
			//System.out.println(RED + "Greeting Message :: " + msg + RESET);

			this.kafkaTemplate.send(kafkaInputTopic, msg);
		}
	}
}
