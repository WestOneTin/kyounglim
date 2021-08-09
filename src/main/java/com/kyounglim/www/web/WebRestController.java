package com.kyounglim.www.web;

import com.kyounglim.www.domain.posts.Posts;
import com.kyounglim.www.dto.file.FileGetResponseDto;
import com.kyounglim.www.dto.file.FileSaveRequestDto;
import com.kyounglim.www.dto.posts.PostSaveRequestDto;
import com.kyounglim.www.dto.posts.PostsGetResponseDto;
import com.kyounglim.www.service.FileService;
import com.kyounglim.www.service.PostsService;
import com.kyounglim.www.util.MD5Generator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@RestController
@AllArgsConstructor // Autowried는 비권장 방식이고 @AllArgsConstructor을 통해 생성자로 주입받는다.
public class WebRestController {

    private PostsService postsService;

    private FileService fileService;

    @GetMapping("/hello")
    public String hello(){
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        return "HelloWorld";
    }

    // Test post list
    @GetMapping("/get/{page}")
    public Page<Posts> list(@PathVariable int page){
        return postsService.findAll(page);
    }

    // Test file getById
    @GetMapping("/getfile/{id}")
    public FileGetResponseDto filelist(@PathVariable("id") Long id){ return fileService.getFile(id); }

    // Test post
    @GetMapping ("/posttest/{id}")
    public PostsGetResponseDto get(@PathVariable("id") Long id){
        return postsService.getById(id);
    }

    // Test Save File
    @PostMapping("/savefile")
    public void savefile(@RequestParam("file") MultipartFile file){
        try {
            String origFilename = file.getOriginalFilename();
            String filename = new MD5Generator(origFilename + ".img").toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\imgs";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            file.transferTo(new File(filePath));

            FileSaveRequestDto filedto = new FileSaveRequestDto();
            filedto.setOrigFilename(origFilename);
            filedto.setFilename(filename);
            filedto.setFilePath(filePath);

            Long fileId = fileService.saveFile(filedto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/save")
    public Long savePost(@RequestParam("file") MultipartFile file, PostSaveRequestDto postdto){//@RequestBody
        try {
            String origFilename = file.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString() + ".jpg";
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if(!new File(savePath).exists()){
                try{
                    new File(savePath).mkdir();
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            file.transferTo(new File(filePath));

            FileSaveRequestDto filedto = new FileSaveRequestDto();
            filedto.setOrigFilename(origFilename);
            filedto.setFilename(filename);
            filedto.setFilePath(filePath);

            Long fileId = fileService.saveFile(filedto);
            postdto.setFileid(fileId);

            return postsService.save(postdto);

        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    @PutMapping("/put/{id}")
    public Posts putPost(@PathVariable("id") Long id , @RequestBody PostSaveRequestDto dto) {
           return postsService.put(id, dto);
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