package com.bookmanagement.controller;

import com.bookmanagement.dto.BookCreateDto;
import com.bookmanagement.dto.BookDto;
import com.bookmanagement.dto.filter.BookFilter;
import com.bookmanagement.dto.response.PageResponse;
import com.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public PageResponse<BookDto> findAll(BookFilter filter, Pageable pageable) {
        Page<BookDto> page = bookService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public BookDto findById(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public BookDto create(@RequestBody BookCreateDto bookDto) {
        return bookService.create(bookDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public BookDto update(@PathVariable("id") Long id,
                              @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bookService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
