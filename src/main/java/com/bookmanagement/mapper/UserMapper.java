package com.bookmanagement.mapper;

import com.bookmanagement.dto.UserDto;
import com.bookmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> user);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto user);

    List<User> toEntityList(List<UserDto> user);
}
