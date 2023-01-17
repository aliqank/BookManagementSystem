package com.bookmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coments")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;

    private String content;

    @Builder.Default()
    private boolean favorite = false;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
