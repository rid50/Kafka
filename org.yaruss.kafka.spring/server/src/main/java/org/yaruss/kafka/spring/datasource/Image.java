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
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @FunctionalInterface
// @Embeddable
// interface FileContent {
    // public byte[] getFileContent(String filePath);
// }

@Entity // This tells Hibernate to make a table out of this class
@Table(name="images")
//@Data
@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
	@Id
    //@Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)	
	// @GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	
    @Column(name = "description")
	String title;
	
    @Column(name = "image")	
	String filePath;
	
    @ManyToOne
    @JoinColumn(name = "album_id") // Specifies the foreign key column name	
	Album album;	
}

@Entity
@Table(name="albums")
//@Data
@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Album {
    @Id
    Integer id;

    @Column(name = "description")
	String title;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY) // "album" is the field name in the Image entity
    Set<Image> images; 
}