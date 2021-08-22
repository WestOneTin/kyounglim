package com.kyounglim.www.web;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
import com.kyounglim.www.service.PhotoService;
import com.kyounglim.www.service.PostsService;
import com.kyounglim.www.util.FileHandler;
import com.kyounglim.www.util.MD5Generator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;
    private PhotoService photoService;
    private FileHandler fileHandler;

    @GetMapping("/search/{page}")
    public Page<Posts> search(String data, @PathVariable("page") int page){
        return postsService.search(data, page);
    }


    @PostMapping("/save")
    public Long savePost(@RequestParam(required = false, name = "file") MultipartFile file, PostSaveRequestDto postdto) throws Exception{
        Photo photo = fileHandler.parseFileInfo(file);
        Posts posts = Posts.builder().item(postdto.getItem()).material(postdto.getMaterial()).stock(postdto.getStock()).content(postdto.getContent()).photo(photo).build();
        return postsService.save(posts)
                ;
    }

    @PutMapping("/update/{id}")
    public Posts update(@PathVariable("id") Long id , PostUpdateResponseDto dto) throws Exception {
        Photo dbPhoto = photoService.findById(id); // DB에 파일 가져오기
        Photo photo = null;

        // DB에 파일이 저장 되어 있는지
        if(dbPhoto.equals("") && dbPhoto.equals(null)){ // isEmpty 대신 "" or null 로 DB에 없다는것 체크
            System.out.println("없음");
            if(dto.getFile() != null){
                photo = fileHandler.parseFileInfo(dto.getFile());
            }
        }else { // DB에 하나 이상 있으면
            System.out.println("하나 있음");
            if(dto.getFile().equals("") && dto.getFile().equals(null)){ // 전달받은 파일이 없다면 DB에 있는 파일 삭제
                System.out.println("삭제하러옴");
                photoService.deletePhoto(dbPhoto.getId());
                photo = fileHandler.parseFileInfo(dto.getFile());
            }else{ // 전달된 파일이 존재한다면
                photo = fileHandler.parseFileInfo(dto.getFile());

            }
        }
        Posts post = postsService.update(id, dto, photo);

        return post;
    }

    @DeleteMapping("/del/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postsService.delete(id);
    }
}