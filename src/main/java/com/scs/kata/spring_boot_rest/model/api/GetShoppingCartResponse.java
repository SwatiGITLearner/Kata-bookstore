package com.scs.kata.spring_boot_rest.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class    GetShoppingCartResponse implements Serializable {
    private int shoppingCartId;
    private BigDecimal totalPrice;
    private List<GetShoppingCartItemResponse> shoppingCartItems;

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<GetShoppingCartItemResponse> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<GetShoppingCartItemResponse> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }
}
