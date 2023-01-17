package com.bookmanagement.mapper;

import com.bookmanagement.dto.BookDto;
import com.bookmanagement.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
