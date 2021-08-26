package com.kyounglim.www.dto.posts;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

@Getter
public class PostsGetResponseDto {
    private Long id;
    private String item;
    private String material;
    private int stock;
    private String content;
    private String modifiedDate;
    private Photo photo;

    public PostsGetResponseDto(Posts entity) {
        id = entity.getId();
        item = entity.getItem();
        material = entity.getMaterial();
        stock = entity.getStock();
        content = entity.getContent();
        photo = entity.getPhoto();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /**
     * Java 8 버전
     */
    private String toStringDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy년MM월dd일 HH시mm분ss초");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
