package com.kyounglim.www.web;

import com.kyounglim.erp.dto.posts.PostSaveRequestDto;
import com.kyounglim.erp.service.PostsService;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @PostMapping("/erp/save")
    public Long savePost(@RequestBody PostSaveRequestDto dto){
        return postsService.save(dto);
    }

    @PutMapping("/erp/{id}/put")
    public Long putPost(@RequestBody PostSaveRequestDto dto, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()){
            return ;
        }else {
            dto.setId(id);
            this.postsService.put(dto);
        }
    }

    @DeleteMapping("/delete")
    public Long deletePost(@RequestBody){

    }
}