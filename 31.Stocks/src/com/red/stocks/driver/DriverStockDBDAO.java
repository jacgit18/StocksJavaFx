package com.red.stocks.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.fxml.model.Stock;

public class DriverStockDBDAO {

	public static void main(String[] args) {

		
		StockDBDAO dao = new StockDBDAO();
//		List<Stock> list = dao.findAll();
//		list.forEach(System.out::println);
		
		
		Map<String,String> map = new HashMap<>();
		//map.put("symbol", "eq:ibm-88");
//		map.put("symbol", "eq:googl-101");

//		map.put("price", "gt:1000");
		map.put("price", "lt:1004");
		
		List<Stock> list = dao.findBy(map );
		
		list.forEach(System.out::println);
		
		// controller should clean just code for gui separations of concerns
	}

}
