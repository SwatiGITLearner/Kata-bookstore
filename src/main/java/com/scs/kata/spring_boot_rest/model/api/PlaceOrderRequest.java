package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequest {
    private int cartId;
    private int userId;
}
