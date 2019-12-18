package com.red.stocks.fxml.model;

public class Product {

	private String name;
	private float price;

	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

}