package org.yaruss.kafka.spring.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.PageRequest;

import org.springframework.kafka.core.KafkaTemplate;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;
//import java.util.ArrayList;
//import java.util.stream.IntStream;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

// import java.io.File;
// import java.nio.file.Files;
// import java.io.FileNotFoundException;
// import java.io.IOException;

// @FunctionalInterface
// //@Embeddable
// interface FileContent {
//     public byte[] getFileContent(String filePath);
// }


//@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
@Service
public class ImageService {

	// private final ImageRepository imageRepository;

    //@Autowired
    // public ImageService(ImageRepository imageRepository) {
        // this.imageRepository = imageRepository;
    // }
	
    //private final ImageMapper imageMapper;	
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
	
    //private imageRepository;
    //@Autowired // Or use lombok @RequiredArgsConstructor
    // public ImageService(ImageRepository imageRepository) {
    //     this.imageRepository = imageRepository;
    // }

    // ImageService(imageRepository) {
    //     this.imageRepository = imageRepository; // Must have this!
    // }    

	// ImageService (ImageRepository imageRepository) {
		// this.imageRepository = imageRepository;
	// }
	
// 	public String get() {
// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@: ");				
// 		return "a";
// 	}
	
    // public List<ImageDTO> getAllImages() {
        // return imageRepository.findAll()
                // .stream()
                // .map(ImageMapper::toResponse)
                // .toList();
    // }

    public void processImagesByPage() {
        int pageNumber = 0;
        int pageSize = 1;
        Slice<Image> imageSlice;


		AtomicReference<String> jsonString = new AtomicReference<>("");
		//String jsonString;
			//StringBuilder imageBase64 = new StringBuilder();

			//List<String> imageBase64 = new ArrayList<String>();
		AtomicReference<String> imageBase64 = new AtomicReference<>("");			
		//String imageBase64;
			
        do {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            imageSlice = imageRepository.findAllBy(pageable);
//            imageSlice = imageRepository.findAllBy(pageable).map(ImageMapper::toResponse);

            imageSlice.getContent().forEach(image -> {
                // Process each image record here
                //System.out.println("Processing customer: " + customer.getName());
				ImageDTO imageDTO = imageMapper.toResponse(image);
				
				//Class<?> objectClass = image.getClass();
				//System.out.println("******************************* The type of the object is: *******************" + objectClass.getName());
				// // Output: org.yaruss.kafka.spring.datasource.Image


					
				try {
/*					
					for (ImageDTO image : images) {
						
						jsonString = objectMapper.writeValueAsString(image);
						imageBase64 = Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.UTF_8));
						this.kafkaTemplate.send(kafkaInputTopic, imageBase64);
						//this.kafkaTemplate.send(kafkaInputTopic, jsonString);
						
						// final Runnable task = () -> {
							// IntStream.range(0, 10).forEach(i -> {
								// total[0] += i; // Modifying the array's content is legal						
							// });	
						// };

						// scheduler.schedule(task, 5, TimeUnit.SECONDS);
					
						//Thread.sleep(100);					
					}				
*/
					jsonString.set(objectMapper.writeValueAsString(imageDTO));
					imageBase64.set(Base64.getEncoder().encodeToString(jsonString.get().getBytes(StandardCharsets.UTF_8)));
					this.kafkaTemplate.send(kafkaInputTopic, imageBase64.get());
					
					//Thread.sleep(100);	
					
				} catch (Exception e) {
					throw new RuntimeException("Error converting DTO to base64 string: ", e);
				}	

			
            });

            pageNumber++;
        } while (imageSlice.hasNext()); // Check if there are more pages
		
		
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




	
/* 		
	public byte[] getFileContent(String filePath) {
	//public FileContent fc = (filePath) -> {
		try {
			File myFile = new File("./upload_folder/" + filePath);
			//Path path = Paths.get(filePathString);
			//byte[] byteArray = new byte[(int) myFile.length()];
			if (myFile.exists()) {
				return Files.readAllBytes(myFile.toPath());
				// setPicture(fileContent);
				// Process the bytes (e.g., display in JLabel, send to browser)
			} else
				return null;
		} catch (FileNotFoundException noFile) {
			throw new RuntimeException("File not found", noFile);
		} catch (IOException e) {
			throw new RuntimeException("Error processing file", e); // Re-throw as a runtime exception if unrecoverable at this level
		} finally {
			
		}			

		// return image;
	}	
		 */
		
/*		
		
    String filePath = imageRepository.findById(id).orElseThrow().getPath();
    
    try {
        Path path = Paths.get(filePath);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        
        // 2. Determine content type
        MediaType contentType = MediaType.IMAGE_JPEG; // Or derive from file extension

        // 3. Return the resource with proper headers
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(resource);
    } catch (IOException e) {
        return ResponseEntity.notFound().build();
    }
*/	
    //public ImageDTO getAllImages() {
        // return imageMapper.toResponse(imageRepository.findAll());
    // }	
	
	
	
}