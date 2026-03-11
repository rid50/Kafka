package org.yaruss.kafka.spring.datasource;

import org.springframework.stereotype.Service;
// import java.util.stream.Stream;
// import java.util.stream.Collectors;

//import lombok.experimental.UtilityClass;

//@UtilityClass
@Service
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
        //dto.setId(image.getId());
		if (image.getAlbum() != null)
			dto.setAlbumTitle(image.getAlbum().getTitle());
        dto.setImageTitle(image.getTitle());
        dto.setFilePath(image.getFilePath());
        return dto;
    }	
}
