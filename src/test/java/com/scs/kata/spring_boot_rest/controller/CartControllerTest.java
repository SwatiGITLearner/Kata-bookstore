package com.scs.kata.spring_boot_rest.controller;

import com.scs.kata.spring_boot_rest.model.api.*;
import com.scs.kata.spring_boot_rest.service.ICartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private ICartService cartService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCart_UserFound_ReturnsOk() {
        int userId = 1;
        RetrieveCartResponse expectedResponse = new RetrieveCartResponse();
        when(cartService.getCart(userId)).thenReturn(expectedResponse);

        ResponseEntity<RetrieveCartResponse> response = cartController.getCart(userId);

        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

    @Test
    void getCart_UserNotFound_ReturnsNotFound() {
        int userId = 1;
        when(cartService.getCart(userId)).thenReturn(null);

        ResponseEntity<RetrieveCartResponse> response = cartController.getCart(userId);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void generateNewCartOrder_CreatesCart_ReturnsCreated() {
        AddCartRequest addCartRequest = new AddCartRequest();
        AddCartResponse expectedResponse = new AddCartResponse();
        when(cartService.addCart(addCartRequest)).thenReturn(expectedResponse);

        ResponseEntity<AddCartResponse> response = cartController.generateNewCartOrder(addCartRequest);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(expectedResponse), response);
    }

    @Test
    void generateNewCartOrder_Error_ReturnsOk() {
        AddCartRequest addCartRequest = new AddCartRequest();
        AddCartResponse expectedResponse = new AddCartResponse();
        expectedResponse.setErrorMessage("Error occurred");
        when(cartService.addCart(addCartRequest)).thenReturn(expectedResponse);

        ResponseEntity<AddCartResponse> response = cartController.generateNewCartOrder(addCartRequest);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body(expectedResponse), response);
    }

    @Test
    void updateExistingBooksCart_Success_ReturnsOk() {
        ChangeCartRequest changeCartRequest = new ChangeCartRequest();
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        when(cartService.updateBookQuantity(anyInt(), anyInt(), anyInt())).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.updateExistingBooksCart(changeCartRequest);

        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

    @Test
    void updateExistingBooksCart_Error_ReturnsBadRequest() {
        ChangeCartRequest changeCartRequest = new ChangeCartRequest();
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        expectedResponse.setErrorMessage("Error occurred");
        when(cartService.updateBookQuantity(anyInt(), anyInt(), anyInt())).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.updateExistingBooksCart(changeCartRequest);

        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse), response);
    }

    @Test
    void deleteItemsFromCart_Success_ReturnsOk() throws Exception {
        int cartId = 1;
        int bookId = 1;
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        when(cartService.deleteItemFromCart(cartId, bookId)).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.deleteItemsFromCart(cartId, bookId);

        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

    @Test
    void deleteItemsFromCart_Error_ReturnsBadRequest() throws Exception {
        int cartId = 1;
        int bookId = 1;
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        expectedResponse.setErrorMessage("Error occurred");
        when(cartService.deleteItemFromCart(cartId, bookId)).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.deleteItemsFromCart(cartId, bookId);

        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse), response);
    }

    @Test
    void deleteCart_Success_ReturnsOk() {
        int cartId = 1;
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        when(cartService.deleteCart(cartId)).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.deleteCart(cartId);

        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

    @Test
    void deleteCart_Error_ReturnsBadRequest() {
        int cartId = 1;
        ChangeCartResponse expectedResponse = new ChangeCartResponse();
        expectedResponse.setErrorMessage("Error occurred");
        when(cartService.deleteCart(cartId)).thenReturn(expectedResponse);

        ResponseEntity<ChangeCartResponse> response = cartController.deleteCart(cartId);

        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(expectedResponse), response);
    }
}
