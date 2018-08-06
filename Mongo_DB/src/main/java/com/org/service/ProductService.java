package com.org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.IProductDao;
import com.org.model.Product;

@Service
public class ProductService {
	
	@Autowired IProductDao prodDao;

	static Map<Long, Product> productRepository = new HashMap<>();
	Random random = new Random();
	static {
		Product prod = new Product();
		prod.setId("234");
		prod.setBrandName("MI");
		prod.setName("Mobile");
		productRepository.put(234L, prod);
	}
	public List<Product> getProducts() {
		return prodDao.findAll();
	}

	public void deleteProduct(String id) {
		prodDao.deleteById(id);
	}

	public Product getProductDetails(String id) {
		return prodDao.findById(id);
	}

	public Product addProduct(Product prod) {
		prodDao.deleteAll();
		prodDao.save(prod);
		return prod;
	}
	
	public List<Product> getProductsByNameAndPrice(String name, Long price) {
		// TODO Auto-generated method stub
		return prodDao.findByNameAndPrice(name, price);
	}

}
