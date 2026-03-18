package org.yaruss.kafka.spring.datasource;

import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.PageRequest;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.producer.KafkaProducer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Duration;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImageService {

	@Value("${kafka.input.topic}")
	private String kafkaInputTopic;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ImageMapper imageMapper;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TaskScheduler taskScheduler;	

	private ScheduledFuture<?> scheduledTask;
	private long fixedDelay = 2000L;

	private int pageNumber = 0;

	private boolean finishedProcess = false;
	
	public void processImagesByPage() {
		int pageSize = 1;
		Slice<Image> imageSlice;


		AtomicReference<String> jsonString = new AtomicReference<>("");
		AtomicReference<String> imageBase64 = new AtomicReference<>("");

		// long currentTimeMillis = System.currentTimeMillis();
		// Instant instant = Instant.ofEpochMilli(currentTimeMillis);
		// ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
		// System.out.println("Current Time: " + zonedDateTime);


		// AtomicReference<Long> currentTimeMillis = new AtomicReference<>(0L);
		// AtomicReference<Instant> instant = new AtomicReference<>(Instant.now());
		// AtomicReference<ZonedDateTime> zonedDateTime = new AtomicReference<>(ZonedDateTime.now());
		
		// currentTimeMillis.set(System.currentTimeMillis());
		// instant.set(Instant.ofEpochMilli(currentTimeMillis.get()));
		// zonedDateTime.set(instant.get().atZone(ZoneId.systemDefault()));
		// System.out.println("Current Time: " + zonedDateTime);


		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		imageSlice = imageRepository.findAllBy(pageable);
		//imageSlice = imageRepository.findAllBy(pageable).map(ImageMapper::toResponse);

		System.out.println("********************************* Current Page: " + imageSlice.getNumber());
		
		imageSlice.getContent().forEach(image -> {
		  
			ImageDTO imageDTO = imageMapper.toResponse(image);
		  
			//Class<?> objectClass = image.getClass();
			//System.out.println("******************************* The type of the object is: *******************" + objectClass.getName());
		  // // Output: org.yaruss.kafka.spring.datasource.Image
		  
		  
			try {
				jsonString.set(objectMapper.writeValueAsString(imageDTO));
				imageBase64.set(Base64.getEncoder().encodeToString(jsonString.get().getBytes(StandardCharsets.UTF_8)));
				kafkaTemplate.send(kafkaInputTopic, imageBase64.get());
			} catch (Exception e) {
				throw new RuntimeException("**************************** Error converting DTO to base64 string: ", e);
			}
		  
		  //}, CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS));
		  

		});
		

		pageNumber++;

		if (!imageSlice.hasNext()) {
			try {
				jsonString.set(objectMapper.writeValueAsString(new byte[0]));
				imageBase64.set(Base64.getEncoder().encodeToString(jsonString.get().getBytes(StandardCharsets.UTF_8)));
				kafkaTemplate.send(kafkaInputTopic, imageBase64.get());
			} catch (Exception e) {
				throw new RuntimeException("**************************** Error converting DTO to base64 string: ", e);
			}			
			// String jsonString  = objectMapper.writeValueAsString(new byte[0]);
			// String imageBase64 = Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));
			// kafkaTemplate.send(kafkaInputTopic, imageBase64);
			finishedProcess = true;
			stopTask();
		}

	}
	
	public void startTask() {
		
        // Runnable task = new ScheduledTaskExecutor(); // Your custom Runnable implementation
        // Duration delay = Duration.ofMillis(fixedDelay);

        // scheduledTask = taskScheduler.scheduleWithFixedDelay(task, delay);
		
		if (scheduledTask != null) {
			if (scheduledTask.isCancelled()) {
				if (finishedProcess) {
					pageNumber = 0;
					finishedProcess = false;
				}
			} else return;
			//scheduledTask.cancel(false); // Cancel any existing task	?????
		}
		
		scheduledTask = taskScheduler.scheduleWithFixedDelay(new ScheduledTaskExecutor(), fixedDelay);
	}

	public void stopTask() {
		
		if (scheduledTask != null && !scheduledTask.isCancelled()) {
			boolean canceled = scheduledTask.cancel(true);

			if (canceled) {
				System.out.println("Task stopped.");
			}
		}
	}	
	
	class ScheduledTaskExecutor implements Runnable {
		@Override
		public void run() {
			processImagesByPage();
		}		
	}
	

	@Transactional
	public Image createNewImage(String album, String title) {
		ImageDTO dto = new ImageDTO();
		dto.setAlbumTitle(album);
		dto.setImageTitle(title);

		Image image = imageMapper.toEntity(dto);

		// The save method handles both insert (new entity) and update (existing entity)
		return imageRepository.save(image);
	}
}

