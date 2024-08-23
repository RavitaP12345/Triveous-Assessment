package com.Triveous.E_Commerce_API.repositories;

import com.Triveous.E_Commerce_API.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
