package com.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.bean.Product;
import com.product.persistence.ProductDao;
import com.product.service.ProductServiceImpl;


@SpringBootTest(classes = ProductServiceApplicationTests.class)
public class ProductServiceApplicationTests {
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	@Mock
	private ProductDao productDao;
	private List<Product> productInDB = new ArrayList<Product>();

	@BeforeEach
	void setUp() throws Exception {
		productServiceImpl.setProductDao(productDao);
		Product p1 = new Product((long) 101, "P_001", "productA", "descriptionA", 500);
		Product p2 = new Product((long) 102, "P_002", "productB", "descriptionA", 400);
		productInDB.add(p1);
		productInDB.add(p2);
	}

	@AfterEach
	void tearDown() throws Exception {
		productServiceImpl = null;
		productInDB.clear();

	}

	@Test
	void testGetProductByCode() {
		String code = "P_001";
		Product product = productInDB.stream().filter(c -> c.getCode().equals(code)).collect(Collectors.toList()).get(0);
		Mockito.when(productDao.findByCode(code)).thenReturn(Optional.of(product));
		assertEquals(Optional.of(product), productServiceImpl.getProductByCode(code));
	}

	@Test
	void testGetProductByCodeWhenCodeNotFound() {
		String code = "P_001";
		Mockito.when(productDao.findByCode(code)).thenReturn(null);
		assertEquals(null, productServiceImpl.getProductByCode(code));
	}
	
	@Test
	void testGetAllProduct() {
		Mockito.when(productDao.findAll()).thenReturn(productInDB);
		assertEquals(productInDB, productServiceImpl.listAllProducts());
	}

	@Test
	void testGetAllProductWhenEmptyList() {
		Mockito.when(productDao.findAll()).thenReturn(null);
		assertEquals(null, productServiceImpl.listAllProducts());
	}
}
