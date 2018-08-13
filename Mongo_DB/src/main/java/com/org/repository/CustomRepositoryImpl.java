package com.org.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.org.model.Brand;
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

	@Override
	public List<Product> whereClause(String field1, String value) {
		
		MatchOperation match = Aggregation.match(new Criteria("brand.name").is(value));
		ProjectionOperation projectStage = Aggregation.project("name").and("brand.name").as("brandName");
		Aggregation ag = Aggregation.newAggregation(match,projectStage);
		List<DBObject> dataList =  mongoTemplate.aggregate(ag, "product", DBObject.class).getMappedResults();
		
		List<Product> prodList = new ArrayList<>();
		Iterator<DBObject> it = dataList.iterator();
		while(it.hasNext()) {
			Product prod = new Product();
			Document data = (Document) it.next();
			prod.setName((String)data.get("name"));
			Brand brand = new Brand((String)data.get("brandName"));
			prod.setBrand(brand);
			prodList.add(prod);
		}
		
//		prodList = dataList.stream().map((data)->{
//			Product prod = new Product();
//			prod.setName((String)data.get("name"));
//			Brand brand = new Brand((String)data.get("brandName"));
//			prod.setBrand(brand);
//			return prod;
//		}).collect(Collectors.toList());
		System.out.println(prodList);
		Document docs = new Document();
		docs.put("ss", "sdsds");
		return prodList;
		
	}
	


}
