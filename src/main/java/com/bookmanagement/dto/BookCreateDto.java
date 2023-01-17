package com.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDto {

    private String id;
    private String name;
    private Integer rars;
    private Integer accessLevel;
    private AuthorDto author;
    private List<GenreDto> genres;
}
