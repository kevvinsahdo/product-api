package com.productapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapi.domain.Product;
import com.productapi.dto.ProductDTO;
import com.productapi.repositories.ProductRepository;
import com.productapi.services.exceptions.ProductNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	public List<Product> list() {
		List<Product> products = productRepository.findAll();
		
		return products;
	}
	
	public Product create(ProductDTO newProduct) {
		Product product = new Product(null, newProduct.getName(), newProduct.getImageUrl(), newProduct.getValue());
		
		product = productRepository.save(product);
		
		return product;
	}
	
	public Product edit(Integer id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElse(null);
		
		if (product == null) {
			throw new ProductNotFoundException("Product not found");
		}
		
		product.setName(productDTO.getName());
		product.setImageUrl(productDTO.getImageUrl());
		product.setValue(productDTO.getValue());
		
		product = productRepository.save(product);
		
		return product;
	}
	
	public void delete(Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		
		if (product == null) {
			throw new ProductNotFoundException("Product not found");
		}
		
		productRepository.delete(product);
	}
}
