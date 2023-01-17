package com.bookmanagement.controller;

import com.bookmanagement.dto.BookCreateDto;
import com.bookmanagement.dto.BookDto;
import com.bookmanagement.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public List<BookDto> findAll() {
       return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDto findById(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public BookDto create(@RequestBody BookCreateDto bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable("id") Long id,
                              @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bookService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
