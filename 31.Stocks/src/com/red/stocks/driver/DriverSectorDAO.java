package com.red.stocks.driver;

import com.red.stocks.dao.IQuery;
import com.red.stocks.dao.SectorDAO;
import com.red.stocks.fxml.model.Sector;

public class DriverSectorDAO {

	public static void main(String[] args) {

		
		IQuery<Sector> dao = new SectorDAO();
		dao.findAll().forEach(System.out::println);
		
	}

}
