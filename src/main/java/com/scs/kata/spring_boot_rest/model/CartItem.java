package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    @Column
    private int quantity;

    public CartItem() {
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public Book getBook() {
        return book;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public CartItem(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}