package com.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.IProductDao;
import com.org.model.Product;

@Service
public class ProductService {
	
	@Autowired IProductDao prodDao;

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
		prodDao.save(prod);
		return prod;
	}
	
	public List<Product> getProductsByNameAndPrice(String name, Long price) {
		// TODO Auto-generated method stub
		return prodDao.findByNameAndPrice(name, price);
	}

	public List<Product> getProductsByBrandName(String name,String value) {
		return prodDao.findByBrandName(name, value);
	}

}
