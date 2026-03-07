package org.yaruss.kafka.spring.datasource;

// import java.util.stream.Stream;
// import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageMapper {

    // public ImageDTO toResponse(Image image) {
        // return new ImageDTO(
                // image.getId(), 
                // image.getDescription(), 
                // image.getImage()
                // .stream()
                // .toList());				
    // }
	
    // public static ImageDTO toResponse(Image image) {
        // return ImageDTO.builder()
                // .id(image.getId())
                // .setDescription(image.getDescription())
                // .filePath(image.getFilePath())
                // .build();
    // }
	
    public ImageDTO toResponse(Image image) {
		ImageDTO dto = new ImageDTO();
        dto.id = image.getId();
        dto.setDescription(image.getDescription());
        dto.setFilePath(image.getFilePath());
        return dto;
    }	
}
