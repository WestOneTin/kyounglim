package com.kyounglim.www.web;

import com.kyounglim.erp.dto.posts.PostSaveRequestDto;
import com.kyounglim.erp.service.PostsService;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @PostMapping("/save")
    public Long savePost(@RequestBody PostSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PutMapping("/put")
    public Long putPost(@RequestBody PostsGetResponseDto dto) {
        return postsService.put(dto);
    }

    @DeleteMapping("/delete")
    public Long deletePost(@RequestBody){

    }
}