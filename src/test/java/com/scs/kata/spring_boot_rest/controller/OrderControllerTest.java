package com.scs.kata.spring_boot_rest.controller;

import com.scs.kata.spring_boot_rest.model.api.PlaceOrderRequest;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderResponse;
import com.scs.kata.spring_boot_rest.service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private IOrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_ValidRequest_ReturnsCreatedResponse() {
        PlaceOrderRequest request = new PlaceOrderRequest(); // Initialize with necessary fields
        PlaceOrderResponse response = new PlaceOrderResponse(); // Initialize as needed

        // Mocking the behavior of the order service
        when(orderService.placeOrder(any(PlaceOrderRequest.class))).thenReturn(response);

        // Call the controller method
        ResponseEntity<PlaceOrderResponse> result = orderController.placeOrder(request);

        // Verify the results
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void placeOrder_InvalidRequest_ReturnsBadRequest() {
        PlaceOrderRequest request = new PlaceOrderRequest(); // Initialize as needed
        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setErrorMessage("Invalid order details");

        // Mocking the behavior of the order service to return an error response
        when(orderService.placeOrder(any(PlaceOrderRequest.class))).thenReturn(response);

        // Call the controller method
        ResponseEntity<PlaceOrderResponse> result = orderController.placeOrder(request);

        // Verify the results
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
