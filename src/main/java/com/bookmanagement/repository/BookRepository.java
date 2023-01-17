package com.bookmanagement.repository;

import com.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends
        JpaRepository<Book, Long>,
        FilterBookRepository,
        QuerydslPredicateExecutor<Book> {
}
