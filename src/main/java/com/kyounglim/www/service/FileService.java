package com.kyounglim.www.service;

import com.kyounglim.www.domain.files.File;
import com.kyounglim.www.domain.files.FileRepository;
import com.kyounglim.www.dto.file.FileGetResponseDto;
import com.kyounglim.www.dto.file.FileSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class FileService {
    private FileRepository fileRepository;

    @Transactional
    public Long saveFile(FileSaveRequestDto dto){
        return fileRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public FileGetResponseDto getFile(Long id){
        File file = fileRepository.findById(id).get();

        File entity = File.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();

        FileGetResponseDto dto = new FileGetResponseDto(entity);
        return dto;
    }
}
