package com.scs.kata.spring_boot_rest.service;

import com.scs.kata.spring_boot_rest.model.Book;
import com.scs.kata.spring_boot_rest.repository.IBookRepository;
import com.scs.kata.spring_boot_rest.repository.IPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookRepository bookRepository;

    @Value("20")
    private int PageSize = 20;
    @Autowired
    IPagingRepository pagingRepository;

    @Override
    public Page<Book> searchBook(String searchCriteria, int pageNo) {
        PageRequest pageable = PageRequest.of(pageNo, PageSize);
        return pagingRepository.findByBookTitleContainingIgnoreCase(searchCriteria, pageable);
    }

}
