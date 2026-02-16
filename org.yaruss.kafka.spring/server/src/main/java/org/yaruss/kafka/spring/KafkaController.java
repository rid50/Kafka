package org.yaruss.kafka.spring;

//import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.yaruss.kafka.spring.producer.MessageProducer;
//import org.yaruss.kafka.spring.consumer.MessageConsumer;
//import org.yaruss.kafka.spring.config.KafkaStreamsConfig;

@RestController
public class KafkaController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MessageProducer messageProducer;
	
	//@Autowired
	//KafkaStreamsConfig kafkaStreamsConfig;
	//MessageConsumer messageConsumer;
	
    @RequestMapping("/start_kafka_broker")
    public ResponseEntity<String> get() {
		//messageProducer.consumeMessage("===========================================");
		messageProducer.enableSheduling();
		return ResponseEntity.ok("The request has succeeded."); 
    }

    @RequestMapping("/stop_kafka_broker")
    public ResponseEntity<String> get2() {
		//messageConsumer.consumeMessage("===========================================");		
		messageProducer.disableSheduling();
		return ResponseEntity.ok("The request has succeeded."); 
    }
	
	// public ResponseEntity<Void> checkStatus() {
		// messageProducer.setEnableDisableSheduling();		
		// return ResponseEntity.ok().build(); // 200 OK with no body
    // }
}
