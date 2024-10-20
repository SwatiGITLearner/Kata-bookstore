package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    @Column
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public CartItem(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}