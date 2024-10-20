package com.scs.kata.spring_boot_rest.repository;

import com.scs.kata.spring_boot_rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer>
{
    List<Book> findByBookTitleContainingIgnoreCase(String bookTitle);
}

