package com.product.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.bean.Product;


@Repository
public interface ProductDao extends JpaRepository<Product, String> {
	Optional<Product> findByCode(String code);
}