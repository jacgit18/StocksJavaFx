package com.red.stocks.fxml.dao;

import java.util.ArrayList;
import java.util.List;

import com.red.stocks.dao.IQuery;
import com.red.stocks.fxml.model.Stock;

public class StockDAO implements IQuery<Stock> {

	public List<Stock> findAll() {
		List<Stock> list = new ArrayList<Stock>();
		
		for (int i = 0; i < 10; i++) {
		Stock e = new Stock("stock " + i, "name of stock " + i, 3_000f + i);
		list.add(e);
		}
		
		
		
		
		
		return list;
	}

}
