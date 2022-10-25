package com.example.hello;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FormBoard {
    @NotEmpty(message = "제목을 입력하세요")
    @Size(max=200)
    private String title;
    @NotEmpty(message = "내용을 입력하세요")
    private String content;
}
