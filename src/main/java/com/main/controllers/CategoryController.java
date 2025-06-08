package com.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.repositories.CategoryRepository;
import com.main.repositories.ProductRepository;
import com.main.entities.*;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	 
	 @GetMapping
	 public Page<Category> getAll(@RequestParam(defaultValue = "0") int page,
	                              @RequestParam(defaultValue = "4") int size) {
	     return categoryRepository.findAll(PageRequest.of(page, size));
	 }

	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Category> getById(@PathVariable Long id) {
	        return categoryRepository.findById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	    }
	    
	 
	 // this is for when we get the page, in each product their  category should be given
	 @PostMapping
	 public Category create(@RequestBody Category category) {
	     if (category.getProducts() != null) {
	         for (Product product : category.getProducts()) {
	             product.setCategory(category); 
	         }
	     }
	     return categoryRepository.save(category);
	 }

	    
	 @PutMapping("/{id}")
	 public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category updatedCategory) {
	     Category existingCategory = categoryRepository.findById(id).orElse(null);

	     if (existingCategory == null) {
	         return ResponseEntity.notFound().build();
	     }

	     existingCategory.setName(updatedCategory.getName());
	     Category savedCategory = categoryRepository.save(existingCategory);
	     return ResponseEntity.ok(savedCategory);
	 }

	    
	    
	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        if (!categoryRepository.existsById(id)) {
	        	return "category not fount with id: " + id;
	        }
	        categoryRepository.deleteById(id);
	        return "Category deleted Successfully";
	    }
	    
	    
	    
	    
	    //GET all products under a specific category
	    @GetMapping("/{id}/products")
	    public ResponseEntity<Page<Product>> getPagedProductsByCategory(
	        @PathVariable Long id, 
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "4") int size
	    ) {
	        if (!categoryRepository.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        Page<Product> products = productRepository.findByCategoryId(id, PageRequest.of(page, size));
	        return ResponseEntity.ok(products);
	    }

	    
	    
}
