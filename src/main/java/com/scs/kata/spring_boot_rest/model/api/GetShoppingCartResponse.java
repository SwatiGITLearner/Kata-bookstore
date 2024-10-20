package com.scs.kata.spring_boot_rest.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class    GetShoppingCartResponse implements Serializable {
    private int shoppingCartId;
    private BigDecimal totalPrice;
    private List<GetShoppingCartItemResponse> shoppingCartItems;
}
