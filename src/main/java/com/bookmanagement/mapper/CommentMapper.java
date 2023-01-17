package com.bookmanagement.mapper;

import com.bookmanagement.dto.CommentDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "bookId", source = "book")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);

    @Mapping(target = "book", source = "bookId")
    Comment toEntity(CommentDto commentDto);

    List<Comment> toEntityList(List<CommentDto> commentDto);

    default Long map(Book value){
        Book build = Book.builder()
                .id(value.getId())
                .build();
        return build.getId();

    }

    default Book map(Long value){
        return Book.builder()
                .id(value)
                .build();
    }

}
