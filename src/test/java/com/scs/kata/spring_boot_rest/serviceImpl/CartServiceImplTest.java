package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.model.MyCart;
import com.scs.kata.spring_boot_rest.model.User;
import com.scs.kata.spring_boot_rest.model.api.AddCartRequest;
import com.scs.kata.spring_boot_rest.model.api.AddCartResponse;
import com.scs.kata.spring_boot_rest.model.api.ChangeCartResponse;
import com.scs.kata.spring_boot_rest.model.api.RetrieveCartResponse;
import com.scs.kata.spring_boot_rest.repository.IBookRepository;
import com.scs.kata.spring_boot_rest.repository.ICartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private ICartRepository cartRepository;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private org.modelmapper.ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCart_CartExists_ReturnsCart() {
        int userId = 1;
        MyCart myCart = new MyCart(new User());
        when(cartRepository.findByUserId(userId)).thenReturn(myCart);

        RetrieveCartResponse response = cartService.getCart(userId);

        assertNull(response);
        verify(cartRepository).findByUserId(userId);
    }

    @Test
    void getCart_CartDoesNotExist_ReturnsNull() {
        int userId = 1;
        when(cartRepository.findByUserId(userId)).thenReturn(null);

        RetrieveCartResponse response = cartService.getCart(userId);

        assertNull(response);
    }


    @Test
    void addCart_CartExists_ThrowsInvalidInputException() {
        AddCartRequest request = new AddCartRequest();
        request.setUserId(1);

        when(cartRepository.findByUserId(request.getUserId())).thenReturn(new MyCart(new User()));

        AddCartResponse response = cartService.addCart(request);

        assertNotNull(response);
        assertEquals("A shopping cart already exists for this user.", response.getErrorMessage());
    }

    @Test
    void updateBookQuantity_CartDoesNotExist_ThrowsInvalidInputException() {
        int cartId = 1;
        int bookId = 1;
        int quantity = 2;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        ChangeCartResponse response = cartService.updateBookQuantity(cartId, bookId, quantity);

        assertNotNull(response);
        assertEquals("The specified cart was not found.", response.getErrorMessage());
    }

    @Test
    void deleteItemFromCart_CartDoesNotExist_ThrowsInvalidInputException() throws Exception {
        int cartId = 1;
        int bookId = 1;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        ChangeCartResponse response = cartService.deleteItemFromCart(cartId, bookId);

        assertNotNull(response);
        assertEquals("The specified cart was not found.", response.getErrorMessage());
    }

    @Test
    void deleteCart_CartExists_DeletesCart() {
        int cartId = 1;

        MyCart myCart = new MyCart(new User());
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(myCart));

        ChangeCartResponse response = cartService.deleteCart(cartId);

        assertNotNull(response);
        verify(cartRepository).delete(myCart);
    }

    @Test
    void deleteCart_CartDoesNotExist_ThrowsInvalidInputException() {
        int cartId = 1;

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        ChangeCartResponse response = cartService.deleteCart(cartId);

        assertNotNull(response);
        assertEquals("The specified cart was not found.", response.getErrorMessage());
    }
}
