package com.kyounglim.www.dto.posts;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by dnjstjr0507@gmail.com on 2019. 5. 6.
 * Github : http://github.com/dnjstjr0507
 */

@Getter
@Setter
@NoArgsConstructor // 기본 생성자  생성
public class PostSaveRequestDto {

    private String item;
    private String material;
    private int stock;
    private String content;
    private Photo photo;

    public Posts toEntity() {
        return Posts.builder()
                .item(item)
                .material(material)
                .stock(stock)
                .content(content)
                .build();
    }

}
