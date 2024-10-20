package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class PlaceOrderResponse implements Serializable {
    private String ErrorMessage;

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
