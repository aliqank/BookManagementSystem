package com.bookmanagement.mapper;

import com.bookmanagement.dto.GenreDto;
import com.bookmanagement.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    List<GenreDto> toDtoList(List<Genre> genre);

    Genre toEntity(GenreDto genreDto);

    List<Genre> toEntityList(List<GenreDto> genreDto);
}
