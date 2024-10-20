package com.scs.kata.spring_boot_rest.model.api;


import java.math.BigDecimal;


public class ChangeCartResponse {
    private BigDecimal totalPrice;
    private String ErrorMessage;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
