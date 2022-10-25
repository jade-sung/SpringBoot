package com.example.hello.controller;

import com.example.hello.FormUser;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/hello")
public class UserController {
    private final UserService userService;
    @Autowired
    public  UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(FormUser formUser) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid FormUser formUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!formUser.getPassword().equals(formUser.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try {
            userService.create(formUser.getUsername(), formUser.getEmail(), formUser.getPassword());
        }catch(DataIntegrityViolationException e) {
                e.printStackTrace();
                bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
                return "signup_form";
        }catch(Exception e) {
                e.printStackTrace();
                bindingResult.reject("signupFailed", e.getMessage());
                return "signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login() {
        return "login_form";
    }

}
