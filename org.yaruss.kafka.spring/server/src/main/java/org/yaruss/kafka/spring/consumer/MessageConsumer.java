package org.yaruss.kafka.spring.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.TaskScheduler;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@Component
public class MessageConsumer {

	@Value("${stomp.topic}")
	private String stompTopic;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@KafkaListener(topics = "${kafka.input.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeMessage(String msg) {
		//System.out.println("Message received: " + msg);
		messagingTemplate.convertAndSend(stompTopic, msg);
	}

}
