package com.org.junit;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.model.Brand;
import com.org.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests extends AbstractJunitTest {
	private static String prodId = "";

	public void test_CreateProducts() throws Exception {
		String url = "/product";
		Brand brand = new Brand("Apple");
		Product prod = new Product();
		prod.setName("Laptop");
		prod.setBrand(brand);
		prod.setPrice(80000L);
		MvcResult result = this.mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(prod))).andReturn();
		int statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
		Product product = mapFromJson(result.getResponse().getContentAsString(), Product.class);
		prodId = product.getId();
		assertTrue(prodId != null);

	}

	public void test_getProducts() throws Exception {
		String url = "/products";
		 this.mockMvc.perform(get(url)).
		 	andExpect(MockMvcResultMatchers.status().isOk());
		 	
	}
	
	public void test_getProduct() throws Exception {
		String url = "/product/{id}";
		MvcResult result = this.mockMvc.perform(get(url, prodId)).andReturn();
		assertEquals(200,result.getResponse().getStatus());
		Product prod = mapFromJson(result.getResponse().getContentAsString(), Product.class);
		assertTrue(prod.getName() != null);
	}
	
	public void test_getProductsByBrandName() throws Exception {
		String url ="/products/{name}";
		this.mockMvc.perform(get(url,"Apple")).
		andExpect(MockMvcResultMatchers.status().isOk()).
		andExpect(jsonPath("$[0].brand.name", org.hamcrest.Matchers.is("Apple")));
	}
	
	public void test_UpdateProduct() throws JsonProcessingException, Exception {
		String url = "/product/{id}";
		MvcResult result = this.mockMvc.perform(get(url, prodId)).andReturn();
		Product prod = mapFromJson(result.getResponse().getContentAsString(), Product.class);
		prod.setPrice(85000L);
		
		url = "/product";
		result = this.mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(prod))).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		Product product = mapFromJson(result.getResponse().getContentAsString(), Product.class);
		assertFalse(product.getPrice() != 85000L);
	}
	
	public void test_DeleteProduct() throws Exception {
		String url = "/product/{id}";
		MvcResult result = this.mockMvc.perform(delete(url, prodId)).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	public void test_ProductByNameAndPrice() throws Exception {
		String url = "/products/{name}/{price}";
		this.mockMvc.perform(get(url,"Laptop", 85000L)).
			andExpect(MockMvcResultMatchers.status().isOk()).
			andExpect(jsonPath("$[0].name", org.hamcrest.Matchers.is("Laptop"))).
			andExpect(jsonPath("$[0].price", is(85000)));
	}

	@Test
	public void execute() throws Exception {
//		test_CreateProducts();
//		test_getProducts();
//		test_getProduct();
		test_getProductsByBrandName();
//		test_UpdateProduct();
//		test_ProductByNameAndPrice();
//		test_DeleteProduct();
	}
}
