package com.kyounglim.www.dto.posts;

import com.kyounglim.www.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private Long id;
    private String item;
    private String material;
    private int stock;
    private String content;
    private Long file_id;
    private String file_filename;

    public PostUpdateResponseDto(Posts entity) {
        id = entity.getId();
        item = entity.getItem();
        material = entity.getMaterial();
        stock = entity.getStock();
        content = entity.getContent();
        file_id = entity.getFiles().getId();
        file_filename = entity.getFiles().getFilename();
    }
}
