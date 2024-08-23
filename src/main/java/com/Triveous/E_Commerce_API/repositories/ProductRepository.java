package com.Triveous.E_Commerce_API.repositories;

import com.Triveous.E_Commerce_API.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
