package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "bookTitle")
    private String bookTitle;

    @Column(name = "authorName")
    private String authorName;

    @Column(name = "bookPrice")
    private BigDecimal bookPrice;


    public Book(String bookTitle, String authorName, BigDecimal bookPrice) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        }
}
