package com.bookmanagement.mapper;

import com.bookmanagement.dto.UserCreateDto;
import com.bookmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {

    UserCreateDto toDto(User user);

    List<UserCreateDto> toDtoList(List<User> user);


    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateDto user);

    List<User> toEntityList(List<UserCreateDto> user);
}
