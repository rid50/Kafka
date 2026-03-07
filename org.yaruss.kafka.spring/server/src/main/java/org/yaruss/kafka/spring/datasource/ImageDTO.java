package org.yaruss.kafka.spring.datasource;

//import org.springframework.stereotype.Component;
//import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;

//import org.jspecify.annotations.Nullable;

//import jakarta.persistence.*;

import lombok.*;
//import lombok.experimental.FieldDefaults;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
// import java.io.FileNotFoundException;
import java.io.IOException;


/* @FunctionalInterface
//@Embeddable
interface FileContent {
    public byte[] getFileContent(String filePath);
}
 */
//@Component
// @Builder
@Data
// @AllArgsConstructor
// @NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class ImageDTO(int id, String description, String filePath) {
public class ImageDTO {

	// public ImageDTO() {
	// 	this.id = 0;

	// }
	//@Exclude
    //private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired	
	//private ImageService imageService;
	
	public int id;
	
	private String description;
	
	@Transient
	@JsonIgnore
	//@Setter(AccessLevel.NONE)
	private String filePath;

	public void setFilePath(String filePath) {
System.out.println("============================================= File path: " + filePath);		
		this.filePath = filePath;
		this.fileContent = getFileContent(filePath);		
	}
 	
 	//@Builder.Default
	@Setter(AccessLevel.NONE)
	private byte[] fileContent;
	//private byte[] fileContent = getFileContent(filePath);	
	
	
	public byte[] getFileContent(String filePath) {
	//public FileContent fc = (filePath) -> {
		System.out.println("***************************************** File path: " + filePath);
		//logger.info("fFile path: " + filePath);
		try {
			File myFile = new File("c:/projects/upload_folder/" + filePath);
		System.out.println("%%%%%%%%%: " + myFile.toPath());

			//Path path = Paths.get(filePathString);
			//byte[] byteArray = new byte[(int) myFile.length()];
			//if (myFile.exists()) {
				return Files.readAllBytes(myFile.toPath());
				// setPicture(fileContent);
				// Process the bytes (e.g., display in JLabel, send to browser)
			//} else
			//	return null;
		// } catch (FileNotFoundException noFile) {
		// System.out.println("$$$$$$$$$$$$$$$$$$$$ File not found ");			
		// 	throw new RuntimeException("File not found", noFile);
		} catch (IOException e) {
			throw new RuntimeException("*********** Error processing file ***********", e); // Re-throw as a runtime exception if unrecoverable at this level
		} finally {
			
		}			

		// return image;
	}	
	
	// public byte[] getFileContent() {
	// 	return fileContent;
	// }	
 
/* 	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public String getFilePath() {
		return filePath;
	}	 */

	
	
/* 	
	byte[] fileContent;
	
 		try {
			File myFile = new File("./upload_folder/" + filePath);
			//Path path = Paths.get(filePathString);
			//byte[] byteArray = new byte[(int) myFile.length()];
			if (myFile.exists()) {
				System.out.println("File is found");				
				fileContent = Files.readAllBytes(myFile.toPath());
				// setPicture(fileContent);
				// Process the bytes (e.g., display in JLabel, send to browser)
			} else {
				System.out.println("File is not found");
				return new byte[0];
			}
		} catch (FileNotFoundException noFile) {
			throw new RuntimeException("File not found", noFile);
		} catch (IOException e) {
			throw new RuntimeException("Error processing file", e); // Re-throw as a runtime exception if unrecoverable at this level
		} finally {
			
		}			
 */	
/* 	
	// public String getImage() {
	FileContent fc = (filePath) -> { 

//		return new byte[0];
 		try {
			File myFile = new File("./upload_folder/" + filePath);
			//Path path = Paths.get(filePathString);
			//byte[] byteArray = new byte[(int) myFile.length()];
			if (myFile.exists()) {
				System.out.println("File is found");				
				return Files.readAllBytes(myFile.toPath());
				// setPicture(fileContent);
				// Process the bytes (e.g., display in JLabel, send to browser)
			} else {
				System.out.println("File is not found");
				return new byte[0];
			}
		} catch (FileNotFoundException noFile) {
			throw new RuntimeException("File not found", noFile);
		} catch (IOException e) {
			throw new RuntimeException("Error processing file", e); // Re-throw as a runtime exception if unrecoverable at this level
		} finally {
			
		}		

		// return image;
	};
 */	
	//staic string fileContent;
	//staic byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
	//byte[] fileContent = fc.getFileContent(filePath);	
	
/*
	private String image;

	private byte[] picture;
	

	// public void setImage(String image) {
		// this.image = image;
	// }
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}	
*/	
}

