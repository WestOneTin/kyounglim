package com.kyounglim.www.web;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.service.PhotoService;
import com.kyounglim.www.service.PostsService;
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


    @GetMapping("/search/{page}")
    public Page<Posts> search(String data, @PathVariable("page") int page){
        return postsService.search(data, page);
    }


    @PostMapping("/save")
    public Long savePost(@RequestParam(required = false, name = "file") MultipartFile file, PostSaveRequestDto postdto){
        try {
                String origFilename = file.getOriginalFilename();
                String filename = new MD5Generator(origFilename).toString() + ".jpg";
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
                //if(filename.equals("d41d8cd98f00b204e9800998ecf8427e.jpg")){
                if(file.isEmpty()){
                    Posts posts = Posts.builder().item(postdto.getItem()).material(postdto.getMaterial()).stock(postdto.getStock()).content(postdto.getContent()).photo(null).build();
                    return postsService.save(posts);
                }else{
                    //
                    String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
                    if (!new File(savePath).exists()) {
                        try {
                            new File(savePath).mkdir();
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    String filePath = savePath + "\\" + filename;
                    file.transferTo(new File(filePath));
                    Photo photo = Photo.builder().origFilename(origFilename).filename(filename).filePath(savePath).build();
                    Posts posts = Posts.builder().item(postdto.getItem()).material(postdto.getMaterial()).stock(postdto.getStock()).content(postdto.getContent()).photo(photo).build();
                    return postsService.save(posts);
                }

        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    @PutMapping("/update/{id}")
    public Posts update(@PathVariable("id") Long id , @RequestBody PostSaveRequestDto dto) {
        Posts post = postsService.update(id, dto);
        MultipartFile file = (MultipartFile) dto.getPhoto();
        if(file.isEmpty()){ //파일이 삭제 되었다면
            photoService.deletePhoto(post.getPhoto().getId());
        }
        if(file!=post.getPhoto()){

        }

        return post;
    }

    @PutMapping("/put-stock/{id}")
    public void stock_edit(@PathVariable("id") Long id, @RequestBody int stock){
        postsService.putStock(id, stock);
    }

    @DeleteMapping("/del/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postsService.delete(id);
    }
}