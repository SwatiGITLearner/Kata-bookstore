package com.scs.kata.spring_boot_rest.model;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import com.scs.kata.spring_boot_rest.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "BookOrder")
public class Order {
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
    @JoinColumn(name = "user_id", nullable = false)
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
