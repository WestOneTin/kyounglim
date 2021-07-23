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

    private PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsGetResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsGetResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostsGetResponseDto findbyid(Long id){
        Posts post = postsRepository.getById(id);
        PostsGetResponseDto postget = new PostsGetResponseDto(post);
        return postget;
    }

    @Transactional
    public Long save(PostSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long put(PostSaveRequestDto dto) { return postsRepository.save(dto.toEntity()).getId();
    }

    /*@Transactional
    public Long delete(Long id){
        return postsRepository.delete(id);
    }*/

}
