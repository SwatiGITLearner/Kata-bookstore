package com.scs.kata.spring_boot_rest.repository;

import com.scs.kata.spring_boot_rest.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {
    public Cart findByUserId(int userId);
}
