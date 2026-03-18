package org.yaruss.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.yaruss.kafka.spring.producer.MessageProducer;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class KafkaController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MessageProducer messageProducer;
	
    @RequestMapping("/start_kafka_broker")
    public ResponseEntity<String> get() {
		//messageProducer.consumeMessage("===========================================");
		messageProducer.start();
		return ResponseEntity.ok("{\"status\":\"ok\"}"); 
    }

    @RequestMapping("/stop_kafka_broker")
    public ResponseEntity<String> get2() {
		//messageConsumer.consumeMessage("===========================================");		
		messageProducer.stop();
		return ResponseEntity.ok("{\"status\":\"ok\"}"); 

//		return ResponseEntity.ok("The request has succeeded."); 
    }
}
