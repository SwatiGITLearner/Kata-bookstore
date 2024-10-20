package com.scs.kata.spring_boot_rest.controller;

import com.scs.kata.spring_boot_rest.model.api.BookResponse;
import com.scs.kata.spring_boot_rest.model.Book;
import com.scs.kata.spring_boot_rest.service.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private IBookService bookService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchBook_ValidCriteria_ReturnsBookResponsePage() {
        String searchCriteria = "testSomeCriteria";
        int pageNo = 0;

        // Mocking the behavior of the service
        Book book1 = new Book(); // Assume appropriate fields are set
        Book book2 = new Book(); // Assume appropriate fields are set
        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books, PageRequest.of(pageNo, 10), books.size());

        when(bookService.searchBook(searchCriteria, pageNo)).thenReturn(bookPage);

        BookResponse bookResponse1 = new BookResponse(); // Map to BookResponse as needed
        BookResponse bookResponse2 = new BookResponse(); // Map to BookResponse as needed

        when(modelMapper.map(book1, BookResponse.class)).thenReturn(bookResponse1);
        when(modelMapper.map(book2, BookResponse.class)).thenReturn(bookResponse2);

        // Call the controller method
        Page<BookResponse> response = bookController.searchBook(searchCriteria, pageNo);

        // Verify the results
        assertEquals(2, response.getTotalElements());
        assertEquals(2, response.getContent().size());
        assertEquals(bookResponse1, response.getContent().get(0));
        assertEquals(bookResponse2, response.getContent().get(1));
    }

    @Test
    void searchBook_NoResults_ReturnsEmptyPage() {
        String searchCriteria = "No Results";
        int pageNo = 0;

        // Mocking the behavior of the service to return an empty page
        Page<Book> bookPage = Page.empty();
        when(bookService.searchBook(searchCriteria, pageNo)).thenReturn(bookPage);

        // Call the controller method
        Page<BookResponse> response = bookController.searchBook(searchCriteria, pageNo);

        // Verify the results
        assertEquals(0, response.getTotalElements());
        assertEquals(0, response.getContent().size());
    }
}
