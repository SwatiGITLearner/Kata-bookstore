package com.scs.kata.spring_boot_rest.model.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RetrieveCartResponse implements Serializable {
    private int cartId;
    private BigDecimal totalPrice;
    private List<RetrieveCartItemResponse> cartItemResponses;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<RetrieveCartItemResponse> getCartItemResponses() {
        return cartItemResponses;
    }

    public void setCartItemResponses(List<RetrieveCartItemResponse> cartItemResponses) {
        this.cartItemResponses = cartItemResponses;
    }
}
