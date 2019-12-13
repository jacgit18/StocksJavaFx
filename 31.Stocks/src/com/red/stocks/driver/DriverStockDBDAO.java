package com.red.stocks.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.red.stocks.dao.IQuery;
import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.dao.CategoryDAO;
import com.red.stocks.fxml.model.Stock;

public class DriverStockDBDAO {

	public static void main(String[] args) {
		
//		t1();
		t2();
//		CategoryRunner();

	}
	
	// Whole list in console
	public static void t1() {

		StockDBDAO dao = new StockDBDAO();
		List<Stock> list = dao.findAll();
//		list.forEach(System.out::println);

		for (Stock stock : list) {
			System.out.println(stock);
		}
	}
	
	// Specific item in list 
	public static void t2() {
		StockDBDAO dao = new StockDBDAO();
		Map<String, String> map = new HashMap<>();
		
//		map.put("symbol","eq:ibm-41");
//		map.put("price", "gt:100");
//		map.put("price", "lt:100");
		map.put("category", "eq:e-03");
		map.put("price", "gt:30"); 
		map.put("price", "lt:34"); 


		List<Stock> list = dao.findBy(map);
		list.forEach(System.out::println);

		
	}
	
	
	
	public static void CategoryRunner() {
		IQuery<String> dao = new CategoryDAO();
		dao.findAll().forEach(System.out::println);
	}


}
