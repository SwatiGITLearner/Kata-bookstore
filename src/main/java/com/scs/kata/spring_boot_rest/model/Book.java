package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "bookTitle")
    private String bookTitle;

    public Book() {
    }

    @Column(name = "authorName")
    private String authorName;

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    @Column(name = "bookPrice")
    private BigDecimal bookPrice;


    public Book(String bookTitle, String authorName, BigDecimal bookPrice) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.bookPrice = bookPrice;
        }
}
