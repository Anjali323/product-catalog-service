package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.bean.Product;


public interface ProductService {
	Optional<Product> getProductByCode(String code);
	
	public List<Product> listAllProducts();
}
