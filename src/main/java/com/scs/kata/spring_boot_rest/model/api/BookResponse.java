package com.scs.kata.spring_boot_rest.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class BookResponse implements Serializable {
    private int id;
    private String title;
    private BigDecimal unitPrice;
    private String genres;
}
