package com.kyounglim.www.web;

import com.kyounglim.www.dto.posts.PostUpdateResponseDto;
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

    /*@GetMapping("/{page}")
    public String table(Model model, @PathVariable("page") int page) {
        model.addAttribute("posts", postsService.findAll(page));
        return "main";
    }*/

    @GetMapping("/{page}")
    public String search(String data, @PathVariable("page") int page, Model model){
        model.addAttribute("posts", postsService.search(data, page));
        model.addAttribute("data", data);
        model.addAttribute("page", page);
        return "main";
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/0?data=";
    }

    @GetMapping("/create")
    public String creatview(@RequestParam(required = false) Long id, @RequestParam(required = false) String item, @RequestParam(required = false) String material, @RequestParam(required = false) Integer stock, @RequestParam(required = false) String content, @RequestParam(required = false) Long file_id, @RequestParam(required = false) String file_filename, Model model){
        if(id != null) {
        PostUpdateResponseDto dto = new PostUpdateResponseDto();
        dto.setId(id);
        dto.setItem(item);
        dto.setMaterial(material);
        dto.setStock(stock);
        dto.setContent(content);
        dto.setFile_id(file_id);
        dto.setFile_filename(file_filename);
            System.out.println("dto : " + dto);
            model.addAttribute("post", dto);
        }
        return "create";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model){
        model.addAttribute("post", postsService.getById(id));
        return "post";
    }

}
