package com.Triveous.E_Commerce_API.repositories;

import com.Triveous.E_Commerce_API.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Order, Long> {
}
