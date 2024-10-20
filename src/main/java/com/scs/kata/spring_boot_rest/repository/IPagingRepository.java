package com.scs.kata.spring_boot_rest.repository;

import com.scs.kata.spring_boot_rest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagingRepository extends PagingAndSortingRepository<Book, Integer> {
    Page<Book> findByBookTitleContainingIgnoreCase(String bookTitle, Pageable pageable);
}

