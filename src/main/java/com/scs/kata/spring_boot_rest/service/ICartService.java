package com.scs.kata.spring_boot_rest.service;

import com.scs.kata.spring_boot_rest.model.api.AddCartRequest;
import com.scs.kata.spring_boot_rest.model.api.AddCartResponse;
import com.scs.kata.spring_boot_rest.model.api.ChangeCartResponse;
import com.scs.kata.spring_boot_rest.model.api.RetrieveCartResponse;

public interface ICartService {

    RetrieveCartResponse getCart(int userId);

    AddCartResponse addCart(AddCartRequest addCartRequest);
//
    ChangeCartResponse updateBookQuantity(int cartId, int bookId, int quantity);

    ChangeCartResponse deleteItemFromCart(int cartId, int bookId) throws Exception;

    ChangeCartResponse deleteCart(int cartId);
}
