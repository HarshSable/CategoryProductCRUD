package com.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.entities.Product;
import com.main.repositories.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping
	public Page<Product> getAllProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
	    
	    return productRepository.findAll(PageRequest.of(page, size));
	}

	
	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productRepository.findById(id).orElse(null);
		
	}
	
	@PostMapping
	public Product create(@RequestBody Product product ) {
		return productRepository.save(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
		Product existingProduct = productRepository.findById(id).orElse(null);
		if(existingProduct !=null) {
			existingProduct.setName(updatedProduct.getName());
			existingProduct.setPrice(updatedProduct.getPrice());
			existingProduct.setCategory(updatedProduct.getCategory());
		}
		return productRepository.save(existingProduct); 
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id) {
	    if (!productRepository.existsById(id)) {
	        return "Product not found with ID: " + id;
	    }
	    productRepository.deleteById(id);
	    return "Product deleted successfully";
	}

	
}
