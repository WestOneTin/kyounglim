package com.kyounglim.www.web;

import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/{page}")
    public String search(String data, @PathVariable("page") int page, Model model){
        model.addAttribute("posts", postsService.search(data, page));
        model.addAttribute("totaldata", postsService.totaldata(data));
        model.addAttribute("data", data);
        model.addAttribute("page", page);
        return "main";
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/0?data=";
    }

    @GetMapping("/create")
    public String creatview(@RequestParam(required = false) Long id, Model model){
        if(id != null) {
            Posts post = postsService.getById(id);
            model.addAttribute("post", post);
        }
        return "create";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model){
        Posts post = postsService.getById(id);
        model.addAttribute("post", post);
        return "post";
    }

}
