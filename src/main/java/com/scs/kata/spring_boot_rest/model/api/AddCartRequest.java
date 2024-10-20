package com.scs.kata.spring_boot_rest.model.api;


import java.math.BigDecimal;
import java.util.List;


public class AddCartRequest {
    private int userId;
    private BigDecimal totalPrice;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setShoppingCartItems(List<CreateCartItemRequest> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<CreateCartItemRequest> getShoppingCartItems() {
        return shoppingCartItems;
    }

    private List<CreateCartItemRequest> shoppingCartItems;
}
