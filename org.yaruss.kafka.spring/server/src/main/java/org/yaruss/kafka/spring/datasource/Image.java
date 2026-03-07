package org.yaruss.kafka.spring.datasource;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import jakarta.persistence.Column;

//import org.jspecify.annotations.Nullable;

import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.FieldDefaults;

import java.io.File;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.stream.IntStream;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @FunctionalInterface
// @Embeddable
// interface FileContent {
    // public byte[] getFileContent(String filePath);
// }

@Entity // This tells Hibernate to make a table out of this class
@Table(name="images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	// @GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;

	String description;
	
    @Column(name = "image")	
	String filePath;

	// public String getFilePath() {
	// 	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ File path: " + filePath);

	// 	return filePath;
	// }		
/*	
	//@Builder.Default
	@Transient
	@JsonIgnore
	private byte[] fileContent = getFileContent(description);
	
	
	
	public byte[] getFileContent(String filePath) {
	//public FileContent fc = (filePath) -> {
		System.out.println("***************************************** File desc: " + filePath);
		//logger.info("fFile path: " + filePath);
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
	

	//byte[] fileContent = (filePath) -> {
/* 
	// public String getImage() {
	FileContent fc = (filePath) -> {

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
	};
 */	
	// byte[] bytes = fc.getFileContent(filePath);
	
    // @Embedded
	// Byte[] fileContent = IntStream.range(0, bytes.length)
                              // .mapToObj(i -> bytes[i]) // Map int index to the byte value (autoboxed to Byte)
                              // .toArray(Byte[]::new);
	//@Lob
	// @Embedded
	// byte[] fileContent = fc.getFileContent(filePath);
	
	// public Integer getId() {
		// return id;
	// }

	// public void setId(Integer id) {
		// this.id = id;
	// }

	// public String getDescription() {
		// return description;
	// }

	// public void setDescription(String description) {
		// this.description = description;
	// }
    // @Column(name = "image")
	// public String getImage() {
		// return image;
	// }

	// public void setImage(String image) {
		// this.image = image;
	// }
}

