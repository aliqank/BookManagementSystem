package com.bookmanagement.mapper;

import com.bookmanagement.dto.AuthorDto;
import com.bookmanagement.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    List<AuthorDto> toDtoList(List<Author> authors);

    Author toEntity(AuthorDto authorDto);

    List<Author> toEntityList(List<AuthorDto> authorDto);
}
