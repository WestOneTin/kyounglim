package com.kyounglim.www.web;

import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String table(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }

    /*
    @GetMapping("/new")
    public String new(){
        return "new"
    }*/

    @GetMapping("/post")
    public String post(Long id, Model model){
        model.addAttribute("post", postsService.getById(id));
        return "post";
    }



}
