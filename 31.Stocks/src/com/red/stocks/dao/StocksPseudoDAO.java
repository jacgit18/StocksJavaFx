package com.red.stocks.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.red.stocks.fxml.model.Stock;

public class StocksPseudoDAO implements IQuery<Stock>{
	// Code From Video

	public List<Stock> findAll() {

		List<Stock> list = new ArrayList<Stock>();

		IntStream.rangeClosed(1, 30).forEach(e -> {
			String symbol = "ibm-" + e;
			String name = "international buisness machine" + e;
			float price = 30 + e;
		});
		
		return list;
	}

}
