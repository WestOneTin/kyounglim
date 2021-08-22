package com.kyounglim.www.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    //Photo findById()

    Long deletePhotoById(Long id);

}
