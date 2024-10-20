package com.scs.kata.spring_boot_rest.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Carts")
public class MyCart {

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column
    private BigDecimal totalAmount;

    public MyCart() {
    }

    public int getCartId() {
        return cartId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "myCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    public MyCart(User user) {
        this.totalAmount = BigDecimal.ZERO;
        this.user=user;
    }


    public void calculateTotalPrice() {
        this.totalAmount = cartItems.stream()
                .map(x -> BigDecimal.valueOf(x.getQuantity()).multiply(x.getBook().getBookPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal addBook(Book book, int quantity) {
        if (this.cartItems == null)
            this.cartItems = new ArrayList<>();
        int existingBookIndex = -1;
        for(int i=0;i<this.cartItems.size();i++){
            CartItem currentItem = this.cartItems.get(i);
            if(currentItem.getBook().getBookId() == book.getBookId()){
                existingBookIndex = i;
                break;
            }
        }
        if(existingBookIndex == -1){
            CartItem cartItem = new CartItem(this,book);
            cartItem.updateQuantity(quantity);
            this.cartItems.add(cartItem);
        } else {
            this.cartItems.get(existingBookIndex).updateQuantity(quantity);
        }

        calculateTotalPrice();
        return this.totalAmount;
    }

    public BigDecimal removeBook(Book book) throws Exception {
        int existingBookIndex = -1;
        for(int i=0;i<this.cartItems.size();i++){
            CartItem currentItem = this.cartItems.get(i);
            if(currentItem.getBook().getBookId()== book.getBookId()){
                existingBookIndex = i;
                break;
            }
        }
        if(existingBookIndex == -1){
            throw new Exception("Book not found");
        }
        this.cartItems.remove(existingBookIndex);
        calculateTotalPrice();
        return this.totalAmount;
    }

    public Order createOrder(User user) throws Exception {
        Order order = new Order(user, this.getTotalAmount(), OrderStatus.ORDER_PLACED, new Date());
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < this.getCartItems().size(); i++) {
            order.addOrderItem(this.getCartItems().get(i));
        }
        return order;
    }
}
