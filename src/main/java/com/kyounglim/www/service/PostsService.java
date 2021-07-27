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
    public Optional<Posts> put(Long id, PostSaveRequestDto dto) {
        Optional<Posts> posts = postsRepository.findById(id);
        System.out.println("ID값 : " + posts.get().getId());
        System.out.println("내용 : " + posts.get().getContent());
        posts.ifPresent(post -> {
            post.builder()
                    .photo("수정")
                    .item("수정")
                    .material("수정")
                    .stock("수정")
                    .content("수정")
                    .build();
            postsRepository.save(post);
        });
        return posts;
    }
        public void delete(Long id){
        Optional<Posts> get_post = postsRepository.findById(id);
        get_post.ifPresent(post ->{
            postsRepository.deleteById(post.getId());
        });
    }
}

