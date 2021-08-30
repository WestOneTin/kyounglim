package com.kyounglim.www.service;


import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.photo.PhotoRepository;
import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.domain.posts.PostsRepository;
import com.kyounglim.www.dto.photo.PhotoSaveRequestDto;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.util.FileHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;


    @Transactional(readOnly = true)
    public List<PostsGetResponseDto> search(String data, int page) {
        return postsRepository.findAllByItemOrMaterial(data, PageRequest.of(page, 10, Sort.by("id").descending())).map(PostsGetResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostsGetResponseDto getById(Long id) {
        Posts post = postsRepository.getById(id);
        return new PostsGetResponseDto(post);
    }

    @Transactional(readOnly = true)
    public Integer totaldata(String data) {
        return postsRepository.countByItemContainingOrMaterialContaining(data, data);
    }


    @Transactional
    public Long save(PostSaveRequestDto requestDto, List<MultipartFile> files) throws Exception {
        Posts posts = new Posts(requestDto.getItem(), requestDto.getMaterial(), requestDto.getStock(), requestDto.getContent());

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(Photo photo : photoList) {
                // 파일을 DB에 저장
                posts.addPhoto(photoRepository.save(photo));
            }
        }
       return postsRepository.save(posts).getId();
    }

    @Transactional
    public Posts update(Long id, PostUpdateResponseDto dto, Photo photo) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(dto.getItem(), dto.getMaterial(), dto.getStock(), dto.getContent());
        return post;
    }

    @Transactional
    public void delete(Long id) {
        Optional<Posts> get_post = postsRepository.findById(id);
        get_post.ifPresent(post -> {
            postsRepository.deleteById(post.getId());
        });
    }

    @Transactional
    public void deleteByPhotoId(Long id){
        Posts post = postsRepository.getById(id);
        List<Photo> dbPhotoList = photoRepository.findAllByPostsId(id);
        for (Photo dbPhoto : dbPhotoList){
            photoRepository.deleteById(dbPhoto.getId());
        }
    }
}

