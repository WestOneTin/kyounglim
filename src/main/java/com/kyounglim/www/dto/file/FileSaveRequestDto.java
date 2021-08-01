package com.kyounglim.www.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileSaveRequestDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public File toEntity(){

    }
}

