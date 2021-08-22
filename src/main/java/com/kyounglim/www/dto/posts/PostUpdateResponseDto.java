package com.kyounglim.www.dto.posts;

import com.kyounglim.www.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private Long id;
    private String item;
    private String material;
    private int stock;
    private String content;

    public PostUpdateResponseDto(Posts entity) {
        id = entity.getId();
        item = entity.getItem();
        material = entity.getMaterial();
        stock = entity.getStock();
        content = entity.getContent();
    }
}
