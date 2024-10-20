package com.scs.kata.spring_boot_rest.service;

import com.scs.kata.spring_boot_rest.model.api.GetShoppingCartResponse;

public interface ICartService {

    GetShoppingCartResponse getCart(int userId);

//    CreateCartResponse createCart(CreateCartRequest createCartRequest);
//
//    UpdateShoppingCartResponse updateBookQuantity(int cartId, int bookId, int quantity);
//
//    UpdateShoppingCartResponse removeCartItem(int cartId, int bookId);
//
//    UpdateShoppingCartResponse deleteCart(int cartId);
}
