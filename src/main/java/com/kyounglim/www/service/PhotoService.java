package com.kyounglim.www.service;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.photo.PhotoRepository;
import com.kyounglim.www.dto.photo.PhotoGetResponseDto;
import com.kyounglim.www.dto.photo.PhotoSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    @Transactional
    public Long saveFile(Photo photo) {
        return photoRepository.save(photo).getId();
    }

    @Transactional
    public PhotoGetResponseDto findById(Long id) {
        Photo photo =  photoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        PhotoGetResponseDto dto = new PhotoGetResponseDto(photo);
        return dto;
    }

    @Transactional
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

}
