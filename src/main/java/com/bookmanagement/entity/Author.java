package com.bookmanagement.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "AUTHOR_SEQUENCE")
    private Long id;

    private String authorName;
}
