package com.example.hello.data;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private List<Reply> replyList;
    private User author;
}
