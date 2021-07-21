package com.kyounglim.www.service;


import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long put(PostsGetResponseDto dto){
        Posts dto2 = postsRepository.getById(dto.getId());

        return ;
    }

    @Transactional
    public Long delete(){
        return postsRepository.delete();
    }

    @Transactional(readOnly = true)
    public List<PostsGetResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsGetResponseDto::new)
                .collect(Collectors.toList());
    }
}
