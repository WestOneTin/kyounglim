package com.kyounglim.www.domain.photo;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID", nullable = false)
    private Long id;

    @Column
    private String origFilename;

    @Column
    private String filename;

    @Column
    private String filePath;

    @Builder
    public Photo(String origFilename, String filename, String filePath) {
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
