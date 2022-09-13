package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.bean.Product;
import com.product.persistence.ProductDao;

import lombok.Setter;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	@Setter
	private ProductDao productDao;


	@Override
	public Optional<Product> getProductByCode(String code) {
		return productDao.findByCode(code);
	}

	@Override
	public List<Product> listAllProducts() {
		return productDao.findAll();
	}
}