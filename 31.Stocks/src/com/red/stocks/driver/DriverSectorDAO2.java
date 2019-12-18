package com.red.stocks.driver;

import java.util.HashMap;
import java.util.Map;

import com.red.stocks.dao.IQuery;
import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.fxml.model.Stock;

public class DriverSectorDAO2 {

	public static void main(String[] args) {

		// dao tester
		IQuery<Stock> dao = new StockDBDAO();
		Map<String, String> map = new HashMap<>();
		map.put("categories", "e-01,e-02");
//		map.put("categories", "e-02");
//		map.put("categories", "all");
//		map.put("categories", "");


		map.put("price", "gt:100");

		dao.findBy(map).forEach(System.out::println);
		
	}

}
