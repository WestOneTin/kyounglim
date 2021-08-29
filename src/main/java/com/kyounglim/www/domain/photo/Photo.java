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

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filePath;

    @OneToOne(mappedBy = "photo", orphanRemoval = true)
    @JsonIgnore
    private Posts posts;


    @Builder
    public Photo(String origFilename, String filename, String filePath, Posts posts) {
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
        this.posts = posts;
    }

}
