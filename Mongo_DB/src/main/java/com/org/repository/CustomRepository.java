package com.org.repository;

import java.util.List;

import com.org.model.Product;

public interface CustomRepository {
	public List<Product> sortAndLimitQuery(String sortBy, String order, int limit);
	public List<Product> whereClauseQuery(String field, String value);
	public List<Product> betweenClauseQuery(String field, int min, int max);
	public List<Product> whereClause(String field1, String value);

}
