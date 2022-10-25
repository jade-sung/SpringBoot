package com.example.hello.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //jsp프로젝트에서 하던 db 시퀀스의 역할
    private Integer id; //null이 가능한 Wrapper Class 사용
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT") //컬럼의 속성을 정의. TEXT는 글자수 제한X
    private String content;
    private LocalDateTime createDate; // 실제 테이블의 컬럼명은 create_date로 만들어짐
    @ManyToOne
    private Board board;
    @ManyToOne
    private User author;

    @Builder // 세터를 쓰는대신 빌더패턴
    public Reply(Integer id, String title, String content, LocalDateTime createDate, Board board, User author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.board = board;
        this.author = author;
    }
}
