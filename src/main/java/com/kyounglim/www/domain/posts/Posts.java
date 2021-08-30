package com.kyounglim.www.domain.posts;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kyounglim.www.domain.BaseTimeEntity;
import com.kyounglim.www.domain.photo.Photo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 자동추가 // (기본생성자의 접근권한을 protected로 제한)
@Getter //Entity 에는 Getter 만 생성 Setter 대신 생성자 or Builder로 주입
@Entity // Table과 링크될 클래스임을 나타냄
@DynamicUpdate // 변경 필드만 반영 될 수 있도록 해줌
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id") // 양방향에서 무한 재귀를 차단하기 위해 아래에 @JsonIgnore 이게 더 정확하게 작동
public class Posts extends BaseTimeEntity {

    @Id // 해당테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성 규칙을 나타냄 기본값은 AUTO, MySQL의 auto_increment와 같이 자동증가 하는 정수형 값이 된다.
    @Column(name = "posts_id")
    private Long id;

    @Column(name = "item",  columnDefinition = "TEXT", nullable = false)
    private String item;

    @Column(name = "material", columnDefinition = "TEXT", nullable = false)
    private String material;

    @Column(name = "stock")
    private int stock;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    //@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true) //EAGER 즉시로딩 // LAZY 지연로딩 // mappedBy가 있으면 대상테이블이다 반대편이 주인이다 (Photo가 주인)
    @OneToMany(mappedBy = "posts", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Photo> photo = new HashSet<>();

    public void addPhoto(Photo photo){
        this.photo.add(photo);

        // 게시글에 파일이 저장되어 있지 않은경우
        if(photo.getPosts() != this)
            // 파일 저장
            photo.setPosts(this);
    }

    @Builder
    public Posts(String item, String material, int stock, String content) {
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }


    public void update(String item, String material, int stock, String content){
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }
}
