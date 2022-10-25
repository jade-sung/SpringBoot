package com.example.hello;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FormReply {

    @NotEmpty(message = "내용을 입력하세요")
    private String content;
}
