package com.productapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productapi.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
