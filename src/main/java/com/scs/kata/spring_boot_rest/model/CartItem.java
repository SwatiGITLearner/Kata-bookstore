package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;


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

    public MyCart getCart() {
        return myCart;
    }

    public Book getBook() {
        return book;
    }

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private MyCart myCart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public CartItem(MyCart myCart, Book book) {
        this.myCart = myCart;
        this.book = book;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}