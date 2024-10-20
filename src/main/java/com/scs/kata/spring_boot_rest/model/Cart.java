package com.scs.kata.spring_boot_rest.model;

import com.scs.kata.spring_boot_rest.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    public Cart(User user) {
        this.totalAmount = BigDecimal.ZERO;
        this.user=user;
    }


    public void calculateTotalPrice() {
        this.totalAmount = cartItems.stream()
                .map(x -> BigDecimal.valueOf(x.getQuantity()).multiply(x.getBook().getBookPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

  public void addCartItem(CartItem cartItem) {
        if (this.cartItems == null)
            this.cartItems = new ArrayList<>();
        this.cartItems.add(cartItem);

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
