package com.kyounglim.www.web;

import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    // 테스트용 post list
    @GetMapping("/get")
    public List<PostsGetResponseDto> list(){
        return postsService.findAllDesc();
    }

    @GetMapping ("/getpost/{id}")
    public PostsGetResponseDto get(@PathVariable("id") Long id){
        return postsService.getById(id);
    }

    @PostMapping("/save")
    public Long savePost(@RequestBody PostSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PutMapping("/{id}/put")
    public Posts putPost(@PathVariable("id") Long id , @RequestBody PostSaveRequestDto dto) {
           return postsService.put(id, dto);
    }

    @PutMapping("/{id}/put-stock")
    public Posts stock_edit(@PathVariable("id") Long id, @RequestBody int stock){
        return postsService.put_stock(id, stock);
    }

    @DeleteMapping("/{id}/del")
    public void deletePost(@PathVariable("id") Long id){
        postsService.delete(id);
    }
}