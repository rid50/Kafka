package org.yaruss.kafka.spring.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
// import org.springframework.kafka.core.KafkaTemplate;

@Component
public class MessageConsumer {

	@Value("${stomp.topic}")
	private String stompTopic;

	// @Value("${kafka.input.topic}")
	// private String kafkaInputTopic;

	@Value("${kafka.output.topic}")
	private String kafkaOutputTopic;
	
	// @Autowired
	// private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

//	@KafkaListener(topics = "${kafka.output.topic}", groupId = "${spring.kafka.consumer.group-id}")
	@KafkaListener(topics = "${kafka.input.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage(String msg) {
		System.out.println("Message received: " + msg);
		//this.kafkaTemplate.send(kafkaOutputTopic, msg);
		messagingTemplate.convertAndSend(stompTopic, msg);
	}

}
