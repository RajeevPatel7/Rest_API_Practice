package com.org.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.model.Product;
import com.org.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements IProductDao {
	
	@Autowired ProductRepository prodRepository;

	@Override
	public List<Product> findAll() {
		return prodRepository.findAll();
	}

	@Override
	public void deleteById(String id) {
		prodRepository.deleteById(id);
		
	}

	@Override
	public Product findById(String id) {
		return prodRepository.findById(id).get();
	}

	@Override
	public void deleteAll() {
		prodRepository.deleteAll();
	}

	@Override
	public void save(Product prod) {
		prodRepository.save(prod);
	}

	@Override
	public List<Product> findByNameAndPrice(String name, Long price) {
		return prodRepository.andClause("name", name, "price", price);
	}

	@Override
	public List<Product> findByBrandName(String name, String value) {
		return prodRepository.whereClause(name, value);
	}
}
