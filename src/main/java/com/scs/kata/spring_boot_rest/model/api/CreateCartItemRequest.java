package com.scs.kata.spring_boot_rest.model.api;



import lombok.Getter;
import lombok.Setter;


public class CreateCartItemRequest {
    private int bookId;
    private int quantity;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }
}
