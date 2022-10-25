package com.example.hello.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //jsp프로젝트에서 하던 db 시퀀스의 역할
    private Integer id; //null이 가능한 Wrapper Class 사용
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT") //컬럼의 속성을 정의. TEXT는 글자수 제한X
    private String content;
    private LocalDateTime createDate; // 실제 테이블의 컬럼명은 create_date로 만들어짐
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE) //Reply의 board와 맵핑됨. board가 삭제되면 replyList도 삭제.
    private List<Reply> replyList;
    @ManyToOne //글쓴이
    private User author;

    @Builder // 세터를 쓰는대신 빌더패턴
    public Board(Integer id, String title, String content, LocalDateTime createDate, List<Reply> replyList, User author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.replyList = replyList;
        this.author = author;
    }

    public void updateBoard(Integer id, String title, String content, LocalDateTime createDate, List<Reply> replyList, User author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.replyList = replyList;
        this.author = author;
    }
}
