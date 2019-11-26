package com.red.stocks.driver;

import java.util.List;

import com.red.stocks.dao.StockDBDAO;
import com.red.stocks.fxml.model.Stock;

public class DriverStockDBDAO {

	public static void main(String[] args) {

		
		StockDBDAO dao = new StockDBDAO();
		List<Stock> list = dao.findAll();
		
		list.forEach(System.out::println);
		
		// controller should clean just code for gui separations of concerns
	}

}
