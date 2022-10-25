package com.example.hello.service;

import com.example.hello.DataNotFoundException;
import com.example.hello.data.Board;
import com.example.hello.data.BoardDto;
import com.example.hello.data.UserDto;
import com.example.hello.mapper.BoardMapper;
import com.example.hello.mapper.UserMapper;
import com.example.hello.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public Page<BoardDto> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Page<Board> boardPage = boardRepository.findAllByKeyword(kw, pageable);
        return boardPage.map(e -> BoardMapper.MAPPER.toDto(e));
    }

    public BoardDto getBoard(Integer id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()){
            return BoardMapper.MAPPER.toDto(board.get());
        }
        else {
            throw new DataNotFoundException("board not found");
        }
    }

    public void create(String title, String content, UserDto userDto) {
        Board board = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).author(UserMapper.MAPPER.toEntity(userDto)).build();
        boardRepository.save(board);
    }

    public void update(BoardDto boardDto, String title, String content) {
        boardDto.setTitle(title);
        boardDto.setContent(content);
        boardRepository.save(BoardMapper.MAPPER.toEntity(boardDto));
    }

    public void delete(BoardDto boardDto) {
        boardRepository.delete(BoardMapper.MAPPER.toEntity(boardDto));
    }
}
