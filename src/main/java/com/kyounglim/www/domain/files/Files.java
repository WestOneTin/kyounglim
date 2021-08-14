package com.kyounglim.www.domain.files;

import com.kyounglim.www.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILES_ID", nullable = false)

    private Long id;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @Builder
    public Files(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
