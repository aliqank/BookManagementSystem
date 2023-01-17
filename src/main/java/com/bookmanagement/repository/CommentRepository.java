package com.bookmanagement.repository;

import com.bookmanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends
        JpaRepository<Comment, Long> {

    List<Comment> findByBookIdAndFavorite(Long bookId, boolean favorite);

    List<Comment> findByBookId(Long bookId);
}
