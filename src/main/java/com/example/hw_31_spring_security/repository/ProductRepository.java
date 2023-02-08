package com.example.hw_31_spring_security.repository;

import com.example.hw_31_spring_security.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    boolean existsProductByName(String productName);
}
