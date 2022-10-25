package com.example.hello.data;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Board board;
    private User author;
}
