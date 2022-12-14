package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {
    @GetMapping("/hello")
    public String index() {
        return "a";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/hello/board";
    }
}
