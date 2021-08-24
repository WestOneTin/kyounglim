package com.kyounglim.www.service;


import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.photo.PhotoRepository;
import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import com.kyounglim.www.dto.photo.PhotoSaveRequestDto;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final PhotoRepository photoRepository;


    @Transactional
    public List<Posts> search(String data, int page) {
        return postsRepository.findAllByItemOrMaterial(data, PageRequest.of(page, 10, Sort.by("id").descending()));
    }

    @Transactional
    public Integer totaldata(String data) {
        return postsRepository.countByItemContainingOrMaterialContaining(data, data);
    }

    @Transactional
    public Posts getById(Long id) {
        return postsRepository.getById(id);
    }

    @Transactional
    public Long save(Posts posts) {
        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Posts update(Long id, PostUpdateResponseDto dto, Photo photo) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        Posts setPost = Posts.builder().item(dto.getItem()).material(dto.getMaterial()).stock(dto.getStock()).content(dto.getContent()).photo(photo).build();
        post.update(setPost);
        return post;
    }

    @Transactional
    public void delete(Long id) {
        Optional<Posts> get_post = postsRepository.findById(id);
        get_post.ifPresent(post -> {
            postsRepository.deleteById(post.getId());
        });
    }
}

