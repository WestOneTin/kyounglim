package com.kyounglim.www.domain.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kyounglim.www.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "origfilename")
    private String origFilename;

    @Column(name = "filepath")
    private String filePath;

    //@OneToOne(mappedBy = "photo", orphanRemoval = true)
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name="posts_id")
    private Posts posts;

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    @Builder
    public Photo(String origFilename, String filePath) {
        this.origFilename = origFilename;
        this.filePath = filePath;
    }

}
