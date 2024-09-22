package com.cardapios.cardapios.repository;

import com.cardapios.cardapios.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
