package com.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String id;
    private String authorName;
    private String content;
    private boolean favorite;
    private String bookId;
}
