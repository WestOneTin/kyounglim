package com.kyounglim.www.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private Long id;
    private String item;
    private String material;
    private int stock;
    private String content;
    private Long file_id;
    private String file_filename;
}
