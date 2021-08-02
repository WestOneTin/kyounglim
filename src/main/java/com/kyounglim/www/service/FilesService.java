package com.kyounglim.www.service;

import com.kyounglim.www.domain.files.Files;
import com.kyounglim.www.domain.files.FilesRepository;
import com.kyounglim.www.dto.file.FileGetResponseDto;
import com.kyounglim.www.dto.file.FileSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class FilesService {
    private FilesRepository filesRepository;

    @Transactional
    public Long saveFile(FileSaveRequestDto dto){
        return filesRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public FileGetResponseDto getFile(Long id){
        Files file = filesRepository.findById(id).get();

        FileGetResponseDto dto = FileGetResponseDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return dto;
    }
}
