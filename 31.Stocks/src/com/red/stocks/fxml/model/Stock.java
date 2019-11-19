package com.red.stocks.fxml.model;

public class Stock {
	
	private String symbol, name;
	
	private Float price;

	public Stock(String symbol, String name, Float price) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", price=" + price + "]";
	}

	
	
	
}
