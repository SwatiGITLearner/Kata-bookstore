package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlaceOrderResponse implements Serializable {
    private String ErrorMessage;
}
