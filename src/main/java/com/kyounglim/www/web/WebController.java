package com.kyounglim.www.web;

import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/{page}")
    public String table(Model model, @PathVariable("page") int page) {
        model.addAttribute("posts", postsService.findAll(page));
        return "main";
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/0";
    }

    @GetMapping("/create")
    public String creatview(){
        return "create";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model){
        model.addAttribute("post", postsService.getById(id));
        return "post";
    }

}
