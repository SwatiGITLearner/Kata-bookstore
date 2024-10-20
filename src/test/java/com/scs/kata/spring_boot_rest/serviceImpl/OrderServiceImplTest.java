package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import com.scs.kata.spring_boot_rest.model.MyCart;
import com.scs.kata.spring_boot_rest.model.Order;
import com.scs.kata.spring_boot_rest.model.User;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderRequest;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderResponse;
import com.scs.kata.spring_boot_rest.repository.ICartRepository;
import com.scs.kata.spring_boot_rest.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private ICartRepository cartRepository;

    private PlaceOrderRequest placeOrderRequest;
    private MyCart myCart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        placeOrderRequest = new PlaceOrderRequest();
        placeOrderRequest.setCartId(1); // Example cart ID
        myCart = new MyCart();
        myCart.setCartId(1); // Set the cart ID to match the request
    }

//    @Test
//    void placeOrder_ValidRequest_Success() throws Exception {
//        // Mock the cart repository to return a cart
//        when(cartRepository.findById(placeOrderRequest.getCartId())).thenReturn(java.util.Optional.of(myCart));
//
//        // Mock the order creation logic
//        User user = new User();
//        user.setId(1);
//        Order order = new Order(); // Assume order is created correctly
//        when(myCart.createOrder(user)).thenReturn(order);
//
//        // Call the service method
//        PlaceOrderResponse response = orderService.placeOrder(placeOrderRequest);
//
//        // Verify that the order was saved and the cart was deleted
//        verify(orderRepository).save(order);
//        verify(cartRepository).delete(myCart);
//
//        // Verify the response
//        assertEquals(null, response.getErrorMessage());
//    }

    @Test
    void placeOrder_CartNotFound_ReturnsError() {
        // Mock the cart repository to return an empty optional
        when(cartRepository.findById(placeOrderRequest.getCartId())).thenReturn(java.util.Optional.empty());

        // Call the service method
        PlaceOrderResponse response = orderService.placeOrder(placeOrderRequest);

        // Verify the response
        assertEquals("Cart not found", response.getErrorMessage());
        verify(orderRepository, never()).save(any());
        verify(cartRepository, never()).delete(any());
    }

//    @Test
//    void placeOrder_ErrorWhilePlacingOrder_ReturnsError() throws Exception {
//        // Mock the cart repository to return a cart
//        when(cartRepository.findById(placeOrderRequest.getCartId())).thenReturn(java.util.Optional.of(myCart));
//
//        // Mock the order creation to throw an exception
//        User user = new User();
//        user.setId(1);
//        when(myCart.createOrder(user)).thenThrow(new NullPointerException());
//
//        // Call the service method
//        PlaceOrderResponse response = orderService.placeOrder(placeOrderRequest);
//
//        // Verify the response
//        assertEquals("Error while placing order", response.getErrorMessage());
//        verify(orderRepository, never()).save(any());
//        verify(cartRepository, never()).delete(any());
//    }
}
