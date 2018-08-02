package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.model.Product;
import com.org.service.ProductService;

@RestController
public class ProductController {

	@Autowired ProductService custService;
	
   //get the Product List
    @RequestMapping(value="/products", method=RequestMethod.GET , produces= {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getProducts(){
    	return custService.getProducts();
    	
    }
    
    @RequestMapping(value="/product/{id}", method=RequestMethod.GET , produces= {MediaType.APPLICATION_JSON_VALUE})
    public Product getProductDetails(@PathVariable("id") Long id){
    	return custService.getProductDetails(id);
    	
    }
    
   //add new Product
    @RequestMapping(value= "product", method = RequestMethod.POST, produces= {MediaType.APPLICATION_JSON_VALUE})
    public Product addProduct(@RequestBody Product prod){
    	return custService.addProduct(prod);
    }
    
    
    //Update the product
    @RequestMapping(value="product", method=RequestMethod.PUT, produces= {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product prod) {
    	return custService.addProduct(prod);
    }
    
    //Delete the products by id
    @RequestMapping(value="product/{id}",method=RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") Long id) {
    	custService.deleteProduct(id);
    }
}
