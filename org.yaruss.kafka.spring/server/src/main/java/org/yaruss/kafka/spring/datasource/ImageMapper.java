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

    public Image toEntity(ImageDTO dto) {
		Image image = new Image();
		//Album album = new Album();
		//album.setCover_image("");
		//image.setAlbum(album);

		image.setAlbum(new Album().setId(9));
		//image.setAlbum(new Album().setId(4).setTitle(dto.getAlbumTitle()+ "153").setName("153").setCover_image(""));
		//image.setAlbum(new Album().setTitle(dto.getAlbumTitle()+ "153").setName("153").setCover_image(""));
        image.setTitle(dto.getImageTitle());
		image.setFilePath("");
        return image;
    }	
}
