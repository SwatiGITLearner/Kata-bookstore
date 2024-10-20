package com.scs.kata.spring_boot_rest.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


public class BookResponse implements Serializable {
    private int id;
    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }



    private BigDecimal bookPrice;
}
