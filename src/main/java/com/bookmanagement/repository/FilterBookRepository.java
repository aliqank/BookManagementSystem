package com.bookmanagement.repository;

import com.bookmanagement.dto.filter.BookFilter;
import com.bookmanagement.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FilterBookRepository {

    List<Book> findAllByFilter(BookFilter filter);
}
