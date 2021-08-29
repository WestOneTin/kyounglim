package com.kyounglim.www.service;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.photo.PhotoRepository;
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
    public Long saveFile(PhotoSaveRequestDto dto) {
        return photoRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }

    @Transactional
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

}
