package com.kyounglim.www.service;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.photo.PhotoRepository;
import com.kyounglim.www.dto.photo.PhotoGetResponseDto;
import com.kyounglim.www.dto.photo.PhotoSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class PhotoService {
    private PhotoRepository fileRepository;

    @Transactional
    public Long saveFile(PhotoSaveRequestDto dto){
        return fileRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Photo getFile(Long id){
        return fileRepository.findById(id).get();

    }
}
