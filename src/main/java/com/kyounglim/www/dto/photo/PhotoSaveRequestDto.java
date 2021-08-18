package com.kyounglim.www.dto.photo;

import com.kyounglim.www.domain.photo.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoSaveRequestDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public Photo toEntity(){
        return Photo.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }
}

