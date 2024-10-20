package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public class UpdateShoppingCartResponse {
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
