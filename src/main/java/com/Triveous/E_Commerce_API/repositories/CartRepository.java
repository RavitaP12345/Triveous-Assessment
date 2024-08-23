package com.Triveous.E_Commerce_API.repositories;

import com.Triveous.E_Commerce_API.entities.Cart;
import com.Triveous.E_Commerce_API.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
