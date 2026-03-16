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

// UPDATE images_seq SET next_val = 100

@Entity // This tells Hibernate to make a table out of this class
@Table(name="images")
@Data
//@Getter
//@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name="images_seq", initialValue=100, allocationSize=1)
public class Image {
	@Id
    //@Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="images_seq")
	Integer id;

    @Column(name = "description")
	String title;
	
    @Column(name = "image")	
	String filePath;
	
	@ManyToOne(cascade = CascadeType.MERGE) // Do not create parent Album, provided the Id for the Album is set, add child Image
	//@ManyToOne(cascade = CascadeType.PERSIST) // Create parent Album, provided the Id for the Album is not set with GenerationType.IDENTITY or set without strategy, add child Image
    @JoinColumn(name = "album_id") // Specifies the foreign key column name	
	Album album;	
}

@Entity
@Table(name="albums")
@Data
//@Getter
//@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Album {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;
	public Album setId(Integer value) { this.id = value; return this; }
	
	String name;
	public Album setName(String value) { this.name = value; return this; }
	
    @Column(name = "description")
	String title;
	public Album setTitle(String value) { this.title = value; return this; }
	
	String cover_image;
	public Album setCover_image(String value) { this.cover_image = value; return this; }
	
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY) // "album" is the field name in the Image entity
    Set<Image> images; 
}