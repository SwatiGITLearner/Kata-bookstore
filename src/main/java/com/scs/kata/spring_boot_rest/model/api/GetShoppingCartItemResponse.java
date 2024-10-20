package com.scs.kata.spring_boot_rest.model.api;



import com.scs.kata.spring_boot_rest.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetShoppingCartItemResponse implements Serializable {
    private int quantity;
    private Book book;

}
