package com.example.hello.controller;


import com.example.hello.FormBoard;
import com.example.hello.FormReply;
import com.example.hello.data.BoardDto;
import com.example.hello.data.UserDto;
import com.example.hello.service.BoardService;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/hello")

public class BoardController {

    private final BoardService boardService;
    private final UserService userService;
    @Autowired
    public BoardController(BoardService boardService, UserService userService){
        this.boardService = boardService;
        this.userService = userService;
    }


    @GetMapping("/board")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw){
        Page<BoardDto> paging = boardService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "board_list";
    }

    @GetMapping("/board/detail/{id}")
    public String detail(Model model, @PathVariable Integer id, FormReply formReply) {
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("board", boardDto);
        return "board_detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/board/create")
    public String boardCreate(FormBoard formBoard) {
        return "board_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/create")
    public String boardCreate(@Valid FormBoard formBoard, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors())
            return "board_form";
        UserDto userDto = userService.getUser(principal.getName());
        boardService.create(formBoard.getTitle(), formBoard.getContent(), userDto);
        return "redirect:/hello/board";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/board/update/{id}")
    public String boardUpdate(FormBoard formBoard, @PathVariable Integer id, Principal principal) {
        BoardDto boardDto = boardService.getBoard(id);
        if(!boardDto.getAuthor().getUsername().equals(principal.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        formBoard.setTitle(boardDto.getTitle());
        formBoard.setContent(boardDto.getContent());
        return "board_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@Valid FormBoard formBoard, BindingResult bindingResult, Principal principal, @PathVariable Integer id) {
        if (bindingResult.hasErrors())
            return "board_form";
        BoardDto boardDto = boardService.getBoard(id);
        if (!boardDto.getAuthor().getUsername().equals(principal.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        boardService.update(boardDto, formBoard.getTitle(), formBoard.getContent());
        return String.format("redirect:/hello/board/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("board/delete/{id}")
    public String boardDelete(Principal principal, @PathVariable Integer id) {
        BoardDto boardDto = boardService.getBoard(id);
        if (!boardDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        boardService.delete(boardDto);
        return "redirect:/";
    }
}
