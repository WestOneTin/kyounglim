package com.kyounglim.www.service;


import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsGetResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsGetResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostsGetResponseDto getById(Long id){
        Posts post = postsRepository.getById(id);
        PostsGetResponseDto dto = new PostsGetResponseDto(post);
        return dto;
    }

    @Transactional
    public Long save(PostSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Posts put(Long id, PostSaveRequestDto dto) {
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(dto.getPhoto(), dto.getItem(), dto.getMaterial(), dto.getStock(), dto.getContent()); //JPA영속성 컨텍스트로 인해 생성자로 주입하면 자동으로 repository.saver가 작동되며 update쿼리를 실행
        return post;
    }

    //stock만 수정하기
    @Transactional
    public Posts put_stock(Long id, int stock){
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.put_stock(id,stock);
        return post;
    }

    @Transactional
    public void delete(Long id){
        Optional<Posts> get_post = postsRepository.findById(id);
        get_post.ifPresent(post ->{
            postsRepository.deleteById(post.getId());
        });
    }
}

