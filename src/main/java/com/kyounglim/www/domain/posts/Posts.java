package com.kyounglim.www.domain.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kyounglim.www.domain.BaseTimeEntity;
import com.kyounglim.www.domain.photo.Photo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 자동추가 // (기본생성자의 접근권한을 protected로 제한)
@Getter //Entity 에는 Getter 만 생성 Setter 대신 생성자 or Builder로 주입
@Entity // Table과 링크될 클래스임을 나타냄
@DynamicUpdate // 변경 필드만 반영 될 수 있도록 해줌
@JsonNaming
public class Posts extends BaseTimeEntity {

    @Id // 해당테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성 규칙을 나타냄 기본값은 AUTO, MySQL의 auto_increment와 같이 자동증가 하는 정수형 값이 된다.
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(name = "ITEM", columnDefinition = "TEXT", nullable = false)
    private String item;

    @Column(name = "MATERIAL", columnDefinition = "TEXT", nullable = false)
    private String material;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER ) //EAGER 즉시로딩 // LAZY 지연로딩
    //@JsonIgnore // FetchType.LAZY JSON response에서 Files를 제외한다는 뜻 // 즉 Posts  데이터를 가져올때 Files 는 제외
    @JoinColumn(name="PHOTO_ID")
    private Photo photo;


    public void putStock(int stock){
        this.stock = stock;
    }

    @Builder
    public Posts(Long id, String item, String material, int stock, String content, Photo photo) {
        this.id = id;
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
        this.photo = photo;
    }
}
