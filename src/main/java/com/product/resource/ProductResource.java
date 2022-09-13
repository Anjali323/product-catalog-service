package com.product.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.bean.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {
	@Autowired
	private ProductService productService;
	
	
	
	@GetMapping(path = "/all")
	public List<Product> listAllProducts(){
		return productService.listAllProducts();
	}
	@GetMapping(path = "/code/{productCode}")
	public Optional<Product> getProductByCode(@PathVariable("productCode") String productCode){
		return productService.getProductByCode(productCode);
	}
}
