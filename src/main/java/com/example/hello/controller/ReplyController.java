package com.example.hello.controller;

import com.example.hello.FormReply;
import com.example.hello.data.BoardDto;
import com.example.hello.data.ReplyDto;
import com.example.hello.data.UserDto;
import com.example.hello.service.BoardService;
import com.example.hello.service.ReplyService;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/hello")
public class ReplyController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final UserService userService;
    @Autowired
    public ReplyController (BoardService boardService, ReplyService replyService, UserService userService){
        this.boardService = boardService;
        this.replyService = replyService;
        this.userService = userService;
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reply/create/{id}")
    public String replyCreate(Model model, @PathVariable Integer id, @Valid FormReply formReply, BindingResult bindingResult, Principal principal) {
        BoardDto boardDto = boardService.getBoard(id);
        UserDto userDto = userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("board", boardDto);
            return "board_detail";
        }
        replyService.create(boardDto, formReply.getContent(), userDto);
        return String.format("redirect:/hello/board/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/reply/delete/{id}")
    public String replyDelete(@PathVariable Integer id, Principal principal) {
        ReplyDto replyDto = replyService.getReply(id);
        if (!replyDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        replyService.delete(replyDto);
        return String.format("redirect:/hello/board/detail/%s", replyDto.getBoard().getId());
    }
}
