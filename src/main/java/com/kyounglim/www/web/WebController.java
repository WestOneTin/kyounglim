package com.kyounglim.www.web;

import com.kyounglim.www.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/table")
    public String table(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "table";
    }
}
