package com.kyounglim.www.dto.file;

import com.kyounglim.www.domain.files.Files;
import lombok.Getter;

@Getter
public class FileGetResponseDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public FileGetResponseDto(Files entity){
        id = entity.getId();
        origFilename = entity.getOrigFilename();
        filename = entity.getFilename();
        filePath = entity.getFilePath();
    }
}
