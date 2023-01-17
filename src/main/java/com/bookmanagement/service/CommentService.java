package com.bookmanagement.service;

import com.bookmanagement.dto.CommentDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.mapper.CommentMapper;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentMapper commentMapper;

    public List<CommentDto> findCommentsByBookId(Long bookId){
        return commentRepository.findByBookId(bookId).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    public List<CommentDto> findFavoriteCommentsByBookId(Long bookId){
        return commentRepository.findByBookIdAndFavorite(bookId,true).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    public Optional<CommentDto> findById(Long id){
        return commentRepository.findById(id)
                .map(commentMapper::toDto);
    }

    @Transactional
    public CommentDto create(Long bookId,CommentDto commentDto) {

        Optional<Book> book = bookRepository.findById(bookId);
        return Optional.of(book)
                .map(entity -> commentMapper.toEntity(commentDto))
                .map(commentRepository::save)
                .map(commentMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<CommentDto> update(Long id, CommentDto commentDto){
        return commentRepository.findById(id)
                .map(entity ->commentMapper.toEntity(commentDto))
                .map(commentRepository::saveAndFlush)
                .map(commentMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return commentRepository.findById(id)
                .map(entity -> {
                    commentRepository.delete(entity);
                    commentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
