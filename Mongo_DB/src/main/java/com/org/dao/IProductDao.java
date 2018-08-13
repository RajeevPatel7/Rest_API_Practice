package com.org.dao;

import java.util.List;

import com.org.model.Product;


public interface IProductDao {

	List<Product> findAll();

	void deleteById(String id);

	Product findById(String id);

	void deleteAll();

	void save(Product prod);
	
	List<Product> findByNameAndPrice(String name, Long price);
	
	List<Product> findByBrandName(String name, String value);

}
