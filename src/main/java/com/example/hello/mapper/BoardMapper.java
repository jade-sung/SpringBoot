package com.example.hello.mapper;

import com.example.hello.data.Board;
import com.example.hello.data.BoardDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BoardMapper {

    BoardMapper MAPPER = Mappers.getMapper(BoardMapper.class);


    Board toEntity(BoardDto boardDto);

    BoardDto toDto(Board board);

    List<Board> toEntityList(List<BoardDto> boardDtoList);

    List<BoardDto> toDtoList(List<Board> boardList);

}
