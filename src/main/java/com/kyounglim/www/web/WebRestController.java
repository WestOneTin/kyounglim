package com.kyounglim.www.web;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PhotoService;
import com.kyounglim.www.service.PostsService;
import com.kyounglim.www.util.FileHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;
    private PhotoService photoService;
    private FileHandler fileHandler;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("kyounglim");

    @GetMapping("/search/{page}")
    public List<PostsGetResponseDto> search(String data, @PathVariable("page") int page) {
        return postsService.search(data, page);
    }

    @GetMapping("/totaldata/{data}")
    public Integer totaldata(@PathVariable("data") String data) {
        return postsService.totaldata(data);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestParam(required = false, value = "image") List<MultipartFile> files, PostSaveRequestDto requestDto) throws Exception {
        return postsService.save(requestDto, files);
    }

    /*@PutMapping("/update/{id}")
    public void update(@PathVariable("id") Long id, @RequestParam(required = false, name = "file") MultipartFile file, PostUpdateResponseDto dto) throws Exception {
        PostsGetResponseDto post = postsService.getById(id);
        Long photoId = post.getPhoto().getId();
        System.out.println("Photo Id : " + photoId);
        Photo photo = null;
        //Optional<Photo> dbPhoto = photoService.findById(id); // DB에 파일 가져오기
        // DB에 파일이 저장 되어 있는지
        if (post.getPhoto() == null) {
            System.out.println("없음");
            if (!file.isEmpty()) {
                photo = fileHandler.parseFileInfo(file);
                System.out.println("photo : " + photo.getFilename());
            }
        } else { // DB에 하나 이상 있으면
            System.out.println("하나 있음");
            if (file.isEmpty()) { // 전달받은 파일이 없다면
                System.out.println("삭제하러옴");
                System.out.println("Photo Id : " + photoId);
                photoService.deletePhoto(photoId); // DB에 있는 파일 삭제
                photo = fileHandler.parseFileInfo(file);
            } else { // 전달된 파일이 존재한다면
                photo = fileHandler.parseFileInfo(file);

            }
        }
        System.out.println("photo : " + photo.getFilename());
        postsService.update(id, dto, photo);
    }*/

    @DeleteMapping("/del/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postsService.delete(id);
    }

    @DeleteMapping("/delphoto/{id}")
    public void delete(@PathVariable("id") Long id){
        postsService.deleteByPhotoId(id);
    }

}