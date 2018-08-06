package com.org.model;

public class Product {
	String id;
	String name;
	String brandName;
	Long price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return String.format(
                "Product[id='%s' , Name='%s' , BrandName='%s', Price='%d']",
                id, name, brandName, price);
    }
}
