package com.bookmanagement.mapper;

import com.bookmanagement.dto.OrderDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Order;
import com.bookmanagement.entity.User;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.UserRepository;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class, BookMapper.class})
public interface OrderMapper {

    @Mapping(target = "userId", source = "user")
    @Mapping(target = "bookIds", source = "books")
    OrderDto toDto(Order order);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "books", ignore = true)
    Order toEntity(OrderDto orderDto,@Context BookRepository bookRepository, @Context UserRepository userRepository);



    default String setUser(User value){
        return value.getId().toString();
    }

    default List<String> map(List<Book> value){

        return value.stream()
                .map(Book::getId)
                .map(String::valueOf)
                .toList();
    }


}
