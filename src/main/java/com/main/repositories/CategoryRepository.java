package com.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	
}
