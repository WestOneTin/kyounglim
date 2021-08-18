package com.kyounglim.www.dto.photo;

import com.kyounglim.www.domain.photo.Photo;
import lombok.Getter;

@Getter
public class PhotoGetResponseDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public PhotoGetResponseDto(Photo entity){
        id = entity.getId();
        origFilename = entity.getOrigFilename();
        filename = entity.getFilename();
        filePath = entity.getFilePath();
    }
}
