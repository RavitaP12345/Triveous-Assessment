package com.Triveous.E_Commerce_API.repositories;

import com.Triveous.E_Commerce_API.entities.Cart;
import com.Triveous.E_Commerce_API.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart byId);
}
