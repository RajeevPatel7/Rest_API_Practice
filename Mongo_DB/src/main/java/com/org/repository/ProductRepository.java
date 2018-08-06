package com.org.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.org.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>, CustomRepository {
	
	@Query("{$or : [{?0 : ?1}, { ?2 : ?3}]}")
	public List<Product> andClause(String field1, String obj1, String field2, Long obj2);

}
