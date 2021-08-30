package com.kyounglim.www.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    void deleteById(Long id);

    List<Photo> findAllByPostsId(Long id);
}
