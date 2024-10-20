package com.scs.kata.spring_boot_rest.repository;

import com.scs.kata.spring_boot_rest.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItem extends JpaRepository<OrderItem, Integer> {
}
