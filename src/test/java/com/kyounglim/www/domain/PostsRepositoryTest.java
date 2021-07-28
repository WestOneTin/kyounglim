package com.kyounglim.www.domain;

import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith({})
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void clean() {
    }

    @Test
    public void 게시글저장_불러오기(){
        postsRepository.save(Posts.builder()
                .photo("test")
                .item("test")
                .material("test")
                .stock(1)
                .content("test")
                .build());

        //when ( 테스트 하고자 하는 행위 선언 여기선 Posts가 DB에 insert 되는것을 확인하기 위함)
        List<Posts> postsList = postsRepository.findAll();

        //then ( 테스트 결과 검증 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인)
        Posts posts = postsList.get(0);
        assertThat(posts.getPhoto(), is("test"));
        assertThat(posts.getContent(), is("test"));
    }

    @Test
    public void 게시글저장_불러오기_수정(){
        postsRepository.save(Posts.builder()
                .photo("test")
                .item("test")
                .material("test")
                .stock(1)
                .content("test")
                .build());

        //when ( 테스트 하고자 하는 행위 선언 여기선 Posts가 DB에 insert 되는것을 확인하기 위함)
        List<Posts> postsList = postsRepository.findAll();

        //then ( 테스트 결과 검증 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인)
        Posts posts = postsList.get(0);
        assertThat(posts.getPhoto(), is("test"));
        assertThat(posts.getItem(), is("test"));
        assertThat(posts.getMaterial(), is("test"));
        assertThat(posts.getStock(), is(1));
        assertThat(posts.getContent(), is("test"));

        Posts posts1 = postsRepository.getById(0L);
            postsRepository.saveAndFlush(posts1.builder()
                    .id(0L)
                    .photo("수정")
                    .item("수정")
                    .material("수정")
                    .stock(1)
                    .content("수정")
                    .build()
        );

        List<Posts> postsList1 = postsRepository.findAll();

        //then ( 테스트 결과 검증 실제로 DB에 insert 되었는지 확인하기 위해 조회후, 입력된 값 확인)
        Posts posts2 = postsList1.get(0);
        assertThat(posts2.getPhoto(), is("수정"));
        assertThat(posts2.getItem(), is("수정"));
        assertThat(posts2.getMaterial(), is("수정"));
        assertThat(posts2.getStock(), is(1));
        assertThat(posts2.getContent(), is("수정"));
    }
}