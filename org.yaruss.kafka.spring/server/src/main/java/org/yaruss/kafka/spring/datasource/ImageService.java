package org.yaruss.kafka.spring.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
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
	
	@Autowired	
	private ImageRepository imageRepository;

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
	
    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(ImageMapper::toResponse)
                .toList();
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