package com.scs.kata.spring_boot_rest.model;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderTest {

    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        when(user.getId()).thenReturn(1); // Set user ID
    }

    @Test
    void constructor_ValidInput_CreatesOrder() {
        BigDecimal totalPrice = BigDecimal.valueOf(100.00);
        Order order = new Order(user, totalPrice, OrderStatus.ORDER_PLACED, new Date());

        assertNotNull(order);
        assertEquals(user, order.getUser());
        assertEquals(totalPrice, order.getTotalPrice());
        assertEquals(OrderStatus.ORDER_PLACED, order.getStatus());
        assertNotNull(order.getOrderDate());
        assertTrue(order.getOrderItems().isEmpty());
    }

    @Test
    void constructor_NullUser_ThrowsInvalidInputException() {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            new Order(null, BigDecimal.valueOf(100.00), OrderStatus.ORDER_PLACED, new Date());
        });

        assertEquals("Empty user", exception.getMessage());
    }

    @Test
    void addOrderItem_AddsOrderItemSuccessfully() {
        Order order = new Order(user, BigDecimal.valueOf(100.00), OrderStatus.ORDER_PLACED, new Date());
        CartItem cartItem = mock(CartItem.class);
        when(cartItem.getBook()).thenReturn(mock(Book.class));
        when(cartItem.getQuantity()).thenReturn(2);

        order.addOrderItem(cartItem);

        assertEquals(1, order.getOrderItems().size());
        assertNotNull(order.getOrderItems().get(0));
    }

    @Test
    void getOrderDetails_ReturnsCorrectDetails() {
        BigDecimal totalPrice = BigDecimal.valueOf(150.00);
        order = new Order(user, totalPrice, OrderStatus.ORDER_PLACED, new Date());

        assertEquals(0, order.getOrderItems().size());
        assertEquals(totalPrice, order.getTotalPrice());
        assertEquals(OrderStatus.ORDER_PLACED, order.getStatus());
        assertNotNull(order.getOrderDate());
    }
}
