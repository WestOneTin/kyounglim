package com.kyounglim.www.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Mybatis,ibatis 에서는 DAO 의 역할과 같은 DB Layer 접근자 JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성

    // p <- 별칭
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    //SELECT
    //      p.id as id,
    //      p.fileId as fileId,
    //      p.item as item,
    //      p.material as material,
    //      p.stock as stock,
    //      p.content as content
    //from
    //      Posts p
    //ORDER BY
    //      p.id DESC
    Stream<Posts> findAllDesc();

    void deleteById(Long id);



}
