package com.red.stocks.fxml.model;

public class Stock {
	
	private String symbol, name,category;
	private int netIncome;
	private float dividendYield;
	private Float price;


	
	public Stock(String symbol, String name, String category, int netIncome, float dividendYield, Float price) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.category = category;
		this.netIncome = netIncome;
		this.dividendYield = dividendYield;
		this.price = price;
	}

	
	
	public int getNetIncome() {
		return netIncome;
	}


	public void setNetIncome(int netIncome) {
		this.netIncome = netIncome;
	}


	public float getDividendYield() {
		return dividendYield;
	}


	public void setDividendYield(float dividendYield) {
		this.dividendYield = dividendYield;
	}


	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
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
		return "Stock [symbol=" + symbol + ", name=" + name + ", category=" + category + ", netIncome=" + netIncome
				+ ", dividendYield=" + dividendYield + ", price=" + price + "]";
	}

	
	
	
}
