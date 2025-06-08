package com.main.repositories;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
                                                                         // Fetching products by category ID
}
