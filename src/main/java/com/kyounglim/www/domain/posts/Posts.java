package com.kyounglim.www.domain.posts;

import com.kyounglim.www.domain.BaseTimeEntity;
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
public class Posts extends BaseTimeEntity {

    @Id // 해당테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성 규칙을 나타냄 기본값은 AUTO, MySQL의 auto_increment와 같이 자동증가 하는 정수형 값이 된다.
    private Long id;

    @Column
    private Long fileid;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String item;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String material;

    @Column
    private int stock;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    public void update(Long fileid, String item, String material, int stock, String content) {
        this.fileid = fileid;
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }

    public void putStock(int stock){
        this.stock = stock;
    }

    @Builder
    public Posts(Long id, Long fileid, String item, String material, int stock, String content) {
        this.id = id;
        this.fileid = fileid;
        this.item = item;
        this.material = material;
        this.stock = stock;
        this.content = content;
    }
}
