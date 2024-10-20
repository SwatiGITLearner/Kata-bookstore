package com.scs.kata.spring_boot_rest.service;

import com.scs.kata.spring_boot_rest.model.api.PlaceOrderRequest;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderResponse;

public interface IOrderService {
   PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);
}
