package com.scs.kata.spring_boot_rest.model.api;



import com.scs.kata.spring_boot_rest.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class GetShoppingCartItemResponse implements Serializable {
    private int quantity;
    private Book book;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
