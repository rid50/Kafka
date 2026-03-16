package org.yaruss.kafka.spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;

// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

//import org.springframework.stereotype.Service;

import org.yaruss.kafka.spring.service.GreetingService;
//import org.yaruss.kafka.spring.datasource.ScheduledTaskExecutor;
import org.yaruss.kafka.spring.datasource.ImageService;
import org.yaruss.kafka.spring.datasource.ImageDTO;
import org.yaruss.kafka.spring.datasource.ImageRepository;

import java.util.List;
import java.util.Base64;
import java.util.ArrayList;


import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
//@Service
//@RequiredArgsConstructor
//@EnableScheduling
public class MessageProducer {
    // ANSI escape code constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";	

	//private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private boolean isEnabled = true;
	
	// @Value("${kafka.input.topic}")
	// private String kafkaInputTopic;

	
	@Autowired	
	private ImageService imageService;

	// @Autowired	
	// private ScheduledTaskExecutor scheduledTaskExecutor;

	// public MessageProducer(ScheduledTaskExecutor scheduledTaskExecutor) {
		// this.scheduledTaskExecutor = scheduledTaskExecutor;
	// }
	
	public void start() {
		imageService.startTask();
	}

	public void stop() {
		imageService.stopTask();
	}

	//@Scheduled(fixedDelay = 2000)
	@PostConstruct
	public void produce() {
		if (isEnabled) {
			//String separator = RED + "Producer: ****************************************************************" + RESET;
			//System.out.println(separator);		
			//String msg = greetingService.greet();
			//this.kafkaTemplate.send(kafkaInputTopic, msg);
			
    		//List<ImageDTO> images = imageService.getAllImages();
			//imageService.processImagesByPage();
			imageService.startTask();
			

		} else {
			for (int i = 0; i < 50; i++)
				imageService.createNewImage("Album" + i, "Title" + i);
		}
	}
}
