package com.scs.kata.spring_boot_rest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyCartTest {

    private MyCart myCart;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1); // Set user ID
        myCart = new MyCart(user);
        book = mock(Book.class);
    }

    @Test
    void addBook_NewBook_AddsBookToCart() {
        when(book.getBookId()).thenReturn(1);
        when(book.getBookPrice()).thenReturn(BigDecimal.valueOf(10.00));

        BigDecimal totalAmount = myCart.addBook(book, 2);

        assertEquals(1, myCart.getCartItems().size());
        assertEquals(BigDecimal.valueOf(20.00), totalAmount);
    }

    @Test
    void addBook_ExistingBook_UpdatesQuantity() {
        when(book.getBookId()).thenReturn(1);
        when(book.getBookPrice()).thenReturn(BigDecimal.valueOf(10.00));

        myCart.addBook(book, 2);
        BigDecimal totalAmount = myCart.addBook(book, 3);

        assertEquals(1, myCart.getCartItems().size());
        assertEquals(BigDecimal.valueOf(30.00), totalAmount);
    }

    @Test
    void removeBook_BookExists_RemovesBookFromCart() throws Exception {
        when(book.getBookId()).thenReturn(1);
        when(book.getBookPrice()).thenReturn(BigDecimal.valueOf(10.00));

        myCart.addBook(book, 2);
        BigDecimal totalAmount = myCart.removeBook(book);

        assertEquals(0, myCart.getCartItems().size());
        assertEquals(BigDecimal.ZERO, totalAmount);
    }


    @Test
    void calculateTotalPrice_CorrectlyCalculatesTotal() {
        Book book1 = mock(Book.class);
        when(book1.getBookId()).thenReturn(1);
        when(book1.getBookPrice()).thenReturn(BigDecimal.valueOf(10.00));

        Book book2 = mock(Book.class);
        when(book2.getBookId()).thenReturn(2);
        when(book2.getBookPrice()).thenReturn(BigDecimal.valueOf(20.00));

        myCart.addBook(book1, 1);
        myCart.addBook(book2, 1);

        myCart.calculateTotalPrice();

        assertEquals(BigDecimal.valueOf(30.00), myCart.getTotalAmount());
    }

    @Test
    void createOrder_CreatesOrderSuccessfully() throws Exception {
        when(book.getBookId()).thenReturn(1);
        when(book.getBookPrice()).thenReturn(BigDecimal.valueOf(10.00));
        myCart.addBook(book, 2);

        Order order = myCart.createOrder(user);

        assertEquals(OrderStatus.ORDER_PLACED, order.getStatus());
        assertEquals(BigDecimal.valueOf(20.00), order.getTotalPrice());
        assertEquals(1, order.getOrderItems().size());
    }
}
