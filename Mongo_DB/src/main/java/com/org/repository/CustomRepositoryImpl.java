package com.org.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.org.model.Product;

public class CustomRepositoryImpl implements CustomRepository{
	
	@Autowired MongoTemplate mongoTemplate;

	@Override
	public List<Product> sortAndLimitQuery(String sortBy, String order, int limit) {
		Sort sort = new Sort(order.equals("ASC") ? Direction.ASC : Direction.DESC, sortBy);
		
		Query query = new Query();
		query.with(sort);
		query.limit(limit);
		return mongoTemplate.find(query, Product.class);
	}

	@Override
	public List<Product> whereClauseQuery(String field, String value) {
		Criteria criteria = new Criteria();
		criteria.andOperator(
				Criteria.where(field).is(value)
		);
		
		Query query = new Query();
		query.addCriteria(criteria);
		return mongoTemplate.find(query, Product.class);
	}

	@Override
	public List<Product> betweenClauseQuery(String field, int min, int max) {
		
		Criteria criteria = new Criteria();
		criteria.andOperator(
				Criteria.where(field).lte(max),
				Criteria.where(field).gt(min)
				);
			
		Query query = new Query();
		query.addCriteria(criteria);
		
		return mongoTemplate.find(query, Product.class);
	}


}
