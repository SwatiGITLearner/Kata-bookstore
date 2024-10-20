package com.scs.kata.spring_boot_rest.service;

import com.scs.kata.spring_boot_rest.model.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Page<Book> searchBook(String searchCriteria, int pageNo);
}
