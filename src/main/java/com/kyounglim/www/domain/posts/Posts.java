package com.kyounglim.www.domain.posts;

import com.kyounglim.www.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 자동추가 // (기본생성자의 접근권한을 protected로 제한)
@Getter
@Entity // Table과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id // 해당테이블의 PK 필드를 나타냄
    @GeneratedValue // PK 의 생성 규칙을 나타냄 기본값은 AUTO, MySQL의 auto_increment와 같이 자동증가 하는 정수형 값이 된다.
    private Long id;

    @Column
    private String photo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String item;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String material;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String stock;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public void update(String photo, String item, String material, String stock, String content) {
        this.photo = photo;
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }

    @Builder
    public Posts(Long id, String photo, String item, String material, String stock, String content) {
        this.id = id;
        this.photo = photo;
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }
}
