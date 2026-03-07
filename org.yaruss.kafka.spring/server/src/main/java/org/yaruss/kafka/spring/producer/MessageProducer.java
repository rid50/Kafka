package org.yaruss.kafka.spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

//import org.springframework.stereotype.Service;

import org.yaruss.kafka.spring.service.GreetingService;
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
@EnableScheduling
public class MessageProducer {
    // ANSI escape code constants
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";	
	
	private boolean isEnabled = true;
	
	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	// @Autowired	
	// private ImageService imageService;
	
	@Autowired	
	private ImageService imageService;

    //@Autowired
    // public MessageProducer(ImageService imageService) {
    //     this.imageService = imageService;
    // }	


	// private final ImageRepository imageRepository;

    // //@Autowired
    // public MessageProducer(ImageRepository imageRepository) {
    //     this.imageRepository = imageRepository;
    // }

	// @Autowired
	// private GreetingService greetingService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

    //private final KafkaTemplate<String, String> kafkaTemplate;
	
    //@Autowired
    // public MessageProducer(KafkaTemplate<String, String> kafkaTemplate, ImageService imageService) {
    //     this.kafkaTemplate = kafkaTemplate;
	// 	this.imageService = imageService;
    // }
	
	public void enableSheduling() {
		isEnabled = true;
	}

	public void disableSheduling() {
		isEnabled = false;
	}

    //List<ImageDTO> images = imageService.getAllImages();	
    //String st = imageService.get();
//System.out.println("7777777777777777777777: ", s);					
	//@Bean
	//@Scheduled(fixedRate = 1000)
	//@PostConstruct
	//public void produce(KafkaTemplate<String, String> kafkaTemplate) {	
	public void produce() {
		if (isEnabled) {
			System.out.println(RED + "Producer: ****************************************************************" + RESET);		
			String msg = greetingService.greet();
/*
    		List<ImageDTO> images = imageService.getAllImages();					
			//String msg = "aaa";
			//System.out.println(RED + "Greeting Message :: " + msg + RESET);

			ObjectMapper mapper = new ObjectMapper();
			
			String jsonString;
			List<String> base64 = new ArrayList<String>();
			try {
				for (ImageDTO image : images) {
					jsonString = mapper.writeValueAsString(image);
					base64.add(Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8)));
				}
			} catch (Exception e) {
				throw new RuntimeException("Error converting DTO to base64 string: ", e);
			}

			System.out.println(RED + "Message :: " + base64 + RESET);
			
			this.kafkaTemplate.send(kafkaInputTopic, String.join(", ", base64));
*/			
			this.kafkaTemplate.send(kafkaInputTopic, msg);
		}
	}
}
