package com.org.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.org.model.Product;

@Service
public class ProductService {

	static Map<Long, Product> productRepository = new HashMap<>();
	Random random = new Random();
	static {
		Product prod = new Product();
		prod.setId(234L);
		prod.setBrandName("MI");
		prod.setName("Mobile");
		productRepository.put(234L, prod);
	}
	public List<Product> getProducts() {
		return new ArrayList<>(productRepository.values());
	}

	public void deleteProduct(Long id) {
		productRepository.remove(id);
	}

	public Product getProductDetails(Long id) {
		return productRepository.get(id);
	}

	public Product addProduct(Product prod) {
		System.out.println(prod.getId());
		if(prod.getId() == null || prod.getId() == 0) {
			prod.setId(random.nextLong());
		}
		productRepository.put(prod.getId(), prod);
		return prod;
	}

}
