package com.kyounglim.www.dto.photo;

import com.kyounglim.www.domain.photo.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PhotoSaveRequestDto {
    private Long id;
    private String origFilename;
    private String filePath;

    @Builder
    public PhotoSaveRequestDto(String origFilename, String filePath) {
        this.origFilename = origFilename;
        this.filePath = filePath;
    }

    public Photo toEntity(){
        return Photo.builder()
                .origFilename(origFilename)
                .filePath(filePath)
                .build();
    }
}

