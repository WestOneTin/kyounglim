package com.kyounglim.www.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // Mybatis,ibatis 에서는 DAO 의 역할과 같은 DB Layer 접근자 JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성

    // p <- 별칭
    /*SELECT
          p.id as id,
          p.fileId as fileId,
          p.item as item,
          p.material as material,
          p.stock as stock,
          p.content as content
    from
          Posts p
    ORDER BY
          p.id DESC*/
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();

    /*
    select
        posts0_.id as id1_1_,
        posts0_.created_date as created_2_1_,
        posts0_.modified_date as modified3_1_,
        posts0_.content as content4_1_,
        posts0_.fileid as fileid5_1_,
        posts0_.item as item6_1_,
        posts0_.material as material7_1_,
        posts0_.stock as stock8_1_
    from
        posts posts0_
    where
        posts0_.item=?
    or
        posts0_.material
    like
        ? escape ?
    */

    @Query("SELECT p FROM Posts p WHERE p.item LIKE %:data% OR p.material LIKE %:data% ") //?1 는 첫번째 parameter 자리에 있는것을 넣겠다는 뜻
    Page<Posts> findAllByItemContainsOrMaterialContains(String data, Pageable pageable);

    Page<Posts> findAll(Pageable pageable);

    void deleteById(Long id);


}
