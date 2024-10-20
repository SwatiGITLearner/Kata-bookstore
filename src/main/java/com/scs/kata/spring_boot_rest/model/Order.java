package com.scs.kata.spring_boot_rest.model;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Orders")
public class Order {
    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column
    private BigDecimal totalPrice;

    @Enumerated
    @Column
    private OrderStatus status;

    @Column
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Order(User user, BigDecimal totalPrice, OrderStatus status, Date orderDate) {
        if(user == null){
            throw new InvalidInputException("Empty user");
        }
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
        this.orderItems = new ArrayList<>();
    }

    public void addOrderItem(CartItem cartItem){
        OrderItem orderItem = new OrderItem(this, cartItem.getBook(), cartItem.getQuantity());
        this.orderItems.add(orderItem);
    }
}
