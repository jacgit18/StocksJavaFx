package com.red.stocks.dao;

import java.util.ArrayList;
import java.util.List;

import com.red.stocks.fxml.model.Product;

public class productDAO {

	public List<Product> findAll() {
		List<Product> list = new ArrayList<>();
		Product apple = new Product("Apple", 255.00f);
		Product google = new Product("GOOG", 455.00f);
		Product samsung = new Product("SAMS", 355.00f);
		Product ge = new Product("GE", 1355.00f);

		list.add(apple);
		list.add(google);
		list.add(samsung);
		list.add(ge);

		return list;

	}

}
