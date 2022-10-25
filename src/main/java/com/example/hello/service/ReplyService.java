package com.example.hello.service;


import com.example.hello.DataNotFoundException;
import com.example.hello.data.*;
import com.example.hello.mapper.BoardMapper;
import com.example.hello.mapper.ReplyMapper;
import com.example.hello.mapper.UserMapper;
import com.example.hello.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public ReplyDto getReply(Integer id) {
        Optional<Reply> reply = replyRepository.findById(id);
        if (reply.isPresent()){
            ReplyDto replyDto = ReplyMapper.MAPPER.toDto(reply.get());
            return replyDto;
        }
        else {
            throw new DataNotFoundException("reply not found");
        }
    }

    public void create(BoardDto boardDto, String content, UserDto userDto) {
        Reply reply = Reply.builder().content(content).board(BoardMapper.MAPPER.toEntity(boardDto)).author(UserMapper.MAPPER.toEntity(userDto)).build();
        replyRepository.save(reply);
    }

    public void delete(ReplyDto replyDto) {
        replyRepository.delete(ReplyMapper.MAPPER.toEntity(replyDto));
    }

}
