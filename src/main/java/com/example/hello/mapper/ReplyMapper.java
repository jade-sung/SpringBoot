package com.example.hello.mapper;

import com.example.hello.data.Reply;
import com.example.hello.data.ReplyDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReplyMapper {
    ReplyMapper MAPPER = Mappers.getMapper(ReplyMapper.class);
    Reply toEntity(ReplyDto replyDto);

    ReplyDto toDto(Reply reply);

    List<Reply> toEntityList(List<ReplyDto> replyDtoList);

    List<ReplyDto> toDtoList(List<Reply> replyList);
}
