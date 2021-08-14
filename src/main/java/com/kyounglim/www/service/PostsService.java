package com.kyounglim.www.service;


import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;


    @Transactional(readOnly = true)
    public Page<Posts> findAll(int page){
        return postsRepository.findAll(PageRequest.of(page, 10, Sort.by("id").descending()));
    }

    @Transactional
    public Page<Posts> searchtest(String item, String material, int page){
        return postsRepository.findAllByItemContainsOrMaterialContains(item, material, PageRequest.of(page, 10, Sort.by("id").descending()));
    }

    /*@Transactional(readOnly = true)
    public List<PostsGetResponseDto> findAllDesc(final Pageable pageable) {
        return postsRepository.findAllDesc()
                .map(PostsGetResponseDto::new)
                .collect(Collectors.toList());
    }*/

    @Transactional
    public Page<Posts> search(String data, int page){
        return postsRepository.findAllByItemOrMaterial(data, PageRequest.of(page, 10, Sort.by("id").descending()));
    }

    @Transactional
    public PostsGetResponseDto getById(Long id){
        Posts post = postsRepository.getById(id);
        PostsGetResponseDto dto = new PostsGetResponseDto(post);
        return dto;
    }




    @Transactional
    public Long save(Posts posts){
        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Posts put(Long id, PostSaveRequestDto dto) {
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(dto.getItem(), dto.getMaterial(), dto.getStock(), dto.getContent()); // persistence context 영속성 컨텍스트로 인해 업데이트 성공
        return post;
    }


    @Transactional
    public void putStock(Long id, int stock){
        Posts post = postsRepository.getById(id);
        post.putStock(stock);
    }

    @Transactional
    public void delete(Long id){
        Optional<Posts> get_post = postsRepository.findById(id);
        get_post.ifPresent(post ->{
            postsRepository.deleteById(post.getId());
        });
    }
}

