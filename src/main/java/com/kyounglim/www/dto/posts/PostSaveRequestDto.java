package com.kyounglim.www.dto.posts;

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
@NoArgsConstructor
public class PostSaveRequestDto {

    private Long id;
    private String photo;
    private String item;
    private String material;
    private String stock;
    private String content;

    public Posts toEntity(){
        return Posts.builder()
                .photo(photo)
                .item(item)
                .material(material)
                .stock(stock)
                .contents(content)
                .build();
    }
}
