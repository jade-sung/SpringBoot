package com.example.hello.mapper;

import com.example.hello.data.User;
import com.example.hello.data.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<User> toEntityList(List<UserDto> userDtoList);

    List<UserDto> toDtoList(List<User> userLists);
}
