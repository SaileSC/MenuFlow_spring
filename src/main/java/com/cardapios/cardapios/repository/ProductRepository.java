package com.cardapios.cardapios.repository;

import com.cardapios.cardapios.domain.product.Product;
import com.cardapios.cardapios.domain.product.ProductResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int integer);
}
