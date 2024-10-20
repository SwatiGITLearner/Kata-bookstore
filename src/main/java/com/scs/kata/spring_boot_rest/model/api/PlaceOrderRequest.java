package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;


public class PlaceOrderRequest {
    private int cartId;
    private int userId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
