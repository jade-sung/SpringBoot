package com.example.hello;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FormUser {
    @Size(min = 2, max = 10)
    @NotEmpty(message = "ID를 입력하세요")
    private String username;
    @NotEmpty(message = "PASSWORD를 입력하세요")
    private String password;
    @NotEmpty(message = "PASSWORD를 한번 더 입력하세요")
    private String password2;
    @Email
    private String email;
}
