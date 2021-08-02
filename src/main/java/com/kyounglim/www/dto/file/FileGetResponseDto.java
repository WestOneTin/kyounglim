package com.kyounglim.www.dto.file;

import com.kyounglim.www.domain.files.Files;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileGetResponseDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public Files toEntity(){
        Files build = Files.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileGetResponseDto(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
