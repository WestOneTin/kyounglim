package com.kyounglim.www.domain.photo;

import com.kyounglim.www.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID")
    private Long id;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @Column(name = "POSTS_ID")
    private Long postsId;

    @Builder
    public Photo(String origFilename, String filename, String filePath, Long postsId) {
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.postsId = postsId;
    }

}
