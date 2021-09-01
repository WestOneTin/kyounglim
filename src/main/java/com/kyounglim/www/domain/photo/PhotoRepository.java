package com.kyounglim.www.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Modifying
    @Query("DELETE FROM Photo p WHERE p.id = ?1 ")
    void deleteById(Long id);

    List<Photo> findAllByPostsId(Long id);
}
