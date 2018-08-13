package com.org.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.org.controller.ProductController;
import com.org.model.Product;
import com.org.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMocitoTest extends AbstractJunitTest {
	
	@Mock
	ProductService productServiceMock;
	
	@InjectMocks
	ProductController controller;
	
	@Test
	public void test_getProducts() throws Exception {
		
		Product prod = new Product();
		prod.setId("234");
		prod.setName("Mobile");
		List<Product> productList = new ArrayList<>();
		productList.add(prod);
		
		when(productServiceMock.getProducts()).thenReturn(productList);
		
		controller.getProducts();
	}

}
