package com.bookmanagement.repository;

import com.bookmanagement.entity.Book;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, FilterBookRepository, QuerydslPredicateExecutor<Book> {
    @EntityGraph(attributePaths = {"author", "genres"})
    List<Book> findAll();
    @EntityGraph(attributePaths = {"author", "genres"})
    Page<Book> findAll(Predicate predicate, Pageable pageable);
    @EntityGraph(attributePaths = {"author", "genres"})
    Optional<Book> findById(Long id);
}
