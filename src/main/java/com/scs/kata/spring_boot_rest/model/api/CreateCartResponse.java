package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


public class CreateCartResponse implements Serializable {
    private int cartId;
    private BigDecimal totalPrice;
    private String ErrorMessage;

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getCartId() {
        return cartId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
