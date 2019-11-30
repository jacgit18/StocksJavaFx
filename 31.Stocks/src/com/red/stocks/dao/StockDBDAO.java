package com.red.stocks.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.red.stocks.driver.Water;
import com.red.stocks.fxml.model.Stock;

public class StockDBDAO implements IQuery<Stock> {

	public List<Stock> findAll() {
		Gson gson = new Gson();
		List<Stock> stocks = new ArrayList<>();
		
		
		
		try (MongoClient mc = new MongoClient("localhost", 27017);) {
			MongoDatabase db = mc.getDatabase("stockdb");
			
			FindIterable<Document> list = db.getCollection("stocks").find();

//			FindIterable<Document> list = db.getCollection("stocks").find().limit(5); // limited to 5
			// shortcut fore
			for (Document document : list) {
				String json = document.toJson();
				Stock stock = gson.fromJson(json, Stock.class);
				stocks.add(stock);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}		
		
		
		return stocks;
	}

}
