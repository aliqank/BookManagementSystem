package com.bookmanagement.mapper;

import com.bookmanagement.dto.BookCreateDto;
import com.bookmanagement.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookCreateMapper {

    BookCreateDto toDto(Book book);

    Book toEntity(BookCreateDto bookDto);
}
