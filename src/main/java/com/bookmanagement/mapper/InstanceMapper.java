package com.bookmanagement.mapper;

import com.bookmanagement.dto.InstanceDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Instance;
import com.bookmanagement.repository.BookRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface InstanceMapper {

    @Mapping(source = "book", target = "bookId")
    InstanceDto toDto(Instance instance);

    List<InstanceDto> toDtoList(List<Instance> instances);


    @Mapping(source = "bookId", target = "book")
    Instance toEntity(InstanceDto instanceDto, @Context BookRepository bookRepository);

    @Mapping(source = "bookId", target = "book")
    List<Instance> toEntityList(List<InstanceDto> instanceDto, @Context BookRepository bookRepository);

    default String map(Book value){
        return value.getId().toString();
    }

    default Book mapToBook(String value, @Context BookRepository bookRepository){
        return bookRepository.findById(Long.valueOf(value)).orElseThrow();
    }

}
