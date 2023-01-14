package com.bookmanagement.mapper;

import com.bookmanagement.dto.CommentDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "book", target = "bookId")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);

    @Mapping(target = "book", ignore = true)
    Comment toEntity(CommentDto commentDto);

    List<Comment> toEntityList(List<CommentDto> commentDto);

    default String map(Book value) {
        return value.getId().toString();
    }

}
