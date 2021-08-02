package com.kyounglim.www.dto.posts;

import com.kyounglim.www.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

@Getter
public class PostsGetResponseDto{
    private Long id;
    private Long fileId;
    private String item;
    private String material;
    private int stock;
    private String content;
    private String modifiedDate;

    public PostsGetResponseDto(Posts entity) {
        id = entity.getId();
        fileId = entity.getFileId();
        item = entity.getItem();
        material = entity.getMaterial();
        stock = entity.getStock();
        content = entity.getContent();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /**
     * Java 8 버전
     */
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
