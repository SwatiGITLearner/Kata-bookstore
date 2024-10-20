package com.scs.kata.spring_boot_rest.model.api;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateShoppingCartResponse {
    private BigDecimal totalPrice;
    private String ErrorMessage;
}
