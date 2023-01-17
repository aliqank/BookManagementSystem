package com.bookmanagement.service;

import com.bookmanagement.dto.BookCreateDto;
import com.bookmanagement.dto.BookDto;
import com.bookmanagement.dto.filter.BookFilter;
import com.bookmanagement.entity.Genre;
import com.bookmanagement.mapper.BookCreateMapper;
import com.bookmanagement.mapper.BookMapper;
import com.bookmanagement.qerydsl.QPredicates;
import com.bookmanagement.repository.BookRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bookmanagement.entity.QBook.book;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookCreateMapper bookCreateMapper;

    public Page<BookDto> findAll(BookFilter filter, Pageable pageable){
        var predicates = QPredicates.builder()
                .add(filter.authorName(), book.author.authorName::containsIgnoreCase)
                .add(filter.genreName(), book.genres.any().genreName::containsIgnoreCase)
                .build();
        return bookRepository.findAll(predicates,pageable)
                .map(bookMapper::toDto);

    }
    public List<BookDto> findAll(){
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public Optional<BookDto> findById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    @Transactional
    public BookDto create(BookCreateDto bookDto) {
        return Optional.of(bookDto)
                .map(bookCreateMapper::toEntity)
                .map(bookRepository::save)
                .map(bookMapper::toDto)
                .orElseThrow();

    }

    @Transactional
    public Optional<BookDto> update(Long id, BookDto bookDto){
        return bookRepository.findById(id)
                .map(bookRepository::saveAndFlush)
                .map(bookMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return bookRepository.findById(id)
                .map(entity -> {
                    bookRepository.delete(entity);
                    bookRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
