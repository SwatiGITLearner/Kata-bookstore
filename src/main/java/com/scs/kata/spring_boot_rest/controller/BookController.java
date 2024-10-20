package com.scs.kata.spring_boot_rest.controller;

import com.scs.kata.spring_boot_rest.model.api.BookResponse;
import com.scs.kata.spring_boot_rest.service.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    IBookService bookService;

    @Autowired
    ModelMapper modelMapper;

   @GetMapping("search")
    public Page<BookResponse> searchBook(@RequestParam String searchCriteria, int pageNo)
   {
    var bookList = bookService.searchBook(searchCriteria, pageNo);
        return bookList.map(book -> modelMapper.map(book, BookResponse.class));
    }

}
