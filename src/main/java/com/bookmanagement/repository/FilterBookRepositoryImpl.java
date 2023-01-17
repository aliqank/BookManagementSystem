package com.bookmanagement.repository;

import com.bookmanagement.dto.filter.BookFilter;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.QBook;
import com.bookmanagement.qerydsl.QPredicates;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bookmanagement.entity.QBook.*;

@RequiredArgsConstructor
public class FilterBookRepositoryImpl implements FilterBookRepository {

    private final EntityManager entityManager;

    @Override
    public List<Book> findAllByFilter(BookFilter filter) {
        var predicates = QPredicates.builder()
                .add(filter.authorName(),book.author.authorName::containsIgnoreCase)
                .build();

        return new JPAQuery<Book>(entityManager)
                .select(book)
                .from(book)
                .where(predicates)
                .fetch();
    }

}
