package com.kyounglim.www.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Mybatis,ibatis 에서는 DAO 의 역할과 같은 DB Layer 접근자 JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성

    @Query("SELECT p " +
            "FROM Posts p  " +
            "LEFT OUTER JOIN FETCH p.photo f " +
            "WHERE p.item LIKE %:data% OR p.material LIKE %:data% ") //?1 는 첫번째 parameter 자리에 있는것을 넣겠다는 뜻*/
    //select * from posts p left join photo t on p.photo_id = t.photo_id
    List<Posts> findAllByItemOrMaterial(String data, Pageable pageable);

    void deleteById(Long id);

    Optional<Posts> findByPhoto(Long id);

}
