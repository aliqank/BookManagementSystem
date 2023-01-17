package com.bookmanagement.controller;

import com.bookmanagement.dto.CommentDto;
import com.bookmanagement.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("book/{bookId}")
    public List<CommentDto> findCommentsByBookId(@PathVariable("bookId") Long bookId) {
        return commentService.findCommentsByBookId(bookId);
    }

    @GetMapping("favorite/book/{bookId}")
    public List<CommentDto> findFavoriteCommentsByBookId(@PathVariable("bookId") Long bookId) {
        return commentService.findFavoriteCommentsByBookId(bookId);
    }

    @GetMapping("/{id}")
    public CommentDto findById(@PathVariable("id") Long id) {
        return commentService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{bookId}")
    public CommentDto create(@PathVariable("bookId") Long id,@RequestBody CommentDto commentDto) {
        return commentService.create(id,commentDto);
    }

    @PutMapping("/{id}")
    public CommentDto update(@PathVariable("id") Long id,@RequestBody CommentDto commentDto) {
        return commentService.update(id,commentDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return commentService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
