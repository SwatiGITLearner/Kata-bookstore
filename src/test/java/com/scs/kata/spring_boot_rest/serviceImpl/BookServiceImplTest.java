package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.model.Book;
import com.scs.kata.spring_boot_rest.repository.IBookRepository;
import com.scs.kata.spring_boot_rest.repository.IPagingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private IPagingRepository pagingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchBook_ValidCriteria_ReturnsBookPage() {
        String searchCriteria = "someTitle";
        int pageNo = 0;

        // Prepare mock data
        Book book1 = new Book(); // Set appropriate fields for the Book
        Book book2 = new Book(); // Set appropriate fields for the Book
        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books, PageRequest.of(pageNo, 20), books.size());

        // Mocking the behavior of the paging repository
        when(pagingRepository.findByBookTitleContainingIgnoreCase(searchCriteria, PageRequest.of(pageNo, 20)))
                .thenReturn(bookPage);

        // Call the service method
        Page<Book> response = bookService.searchBook(searchCriteria, pageNo);

        // Verify the results
        assertEquals(2, response.getTotalElements());
        assertEquals(2, response.getContent().size());
        assertEquals(book1, response.getContent().get(0));
        assertEquals(book2, response.getContent().get(1));
    }

    @Test
    void searchBook_NoResults_ReturnsEmptyPage() {
        String searchCriteria = "nonExistentTitle";
        int pageNo = 0;

        // Mocking the behavior of the paging repository to return an empty page
        Page<Book> emptyBookPage = Page.empty();
        when(pagingRepository.findByBookTitleContainingIgnoreCase(searchCriteria, PageRequest.of(pageNo, 20)))
                .thenReturn(emptyBookPage);

        // Call the service method
        Page<Book> response = bookService.searchBook(searchCriteria, pageNo);

        // Verify the results
        assertEquals(0, response.getTotalElements());
        assertEquals(0, response.getContent().size());
    }
}
