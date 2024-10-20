package com.scs.kata.spring_boot_rest.repository;

import com.scs.kata.spring_boot_rest.model.MyCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<MyCart, Integer> {
    public MyCart findByUserId(int userId);
}
