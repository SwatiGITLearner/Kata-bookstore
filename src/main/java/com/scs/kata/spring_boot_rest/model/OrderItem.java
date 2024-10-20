package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int quantity;

    public OrderItem() {
    }

    public Book getBook() {
        return book;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderItem(Order order, Book book, int quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }
}