package com.red.stocks.dao;

//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
//import java.util.stream.IntStream;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.red.stocks.fxml.model.Stock;

public class StockDBDAO implements IQuery<Stock> {
	/////////////// Basic Version ////////////////////
//	public List<Stock> findAll() {
//	List<Stock> list = new ArrayList<Stock>();
//	
//	for (int i = 0; i < 10; i++) {
//	Stock e = new Stock("stock " + i, "name of stock " + i, 3_000f + i);
//	list.add(e);
//	}
//	
	
 ////////////////Data Structures functional  Version ///////////////////////////	
//	List<Stock> list = new ArrayList<Stock>();
//
//	IntStream.rangeClosed(1, 30).forEach(e -> {
//		String symbol = "ibm-" + e;
//		String name = "international buisness machine" + e;
//		float price = 30 + e;
//	});
//	
//	
//	
//	return list;
//}

	public List<Stock> findAll() {
		Gson gson = new Gson();
		List<Stock> stocks = new ArrayList<>();
		
		
		
		try (
				MongoClient mc = new MongoClient("localhost", 27017);
				
				) {
			MongoDatabase db = mc.getDatabase("stockdb");
//			MongoCollection<Document> collection = db.getCollection("stocks");
//			FindIterable<Document> list = collection.find();// Alternate


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
	
	@Override
	public List<Stock> findBy(Map<String, String> map) {
		List<Stock> stocks = new ArrayList<Stock>();
		Gson gson = new Gson();
		Bson bson = new Document();
//		Vector<String> v;


			if (map.containsKey("symbol")) {
				String symbolSearch = map.get("symbol");
				String[] parts = symbolSearch.split(":");
				
				if (parts[1].equalsIgnoreCase("all")) {
					
				}
				
				else if (parts[0].contentEquals("eq") ) {
					bson = eq("symbol", parts[1]);
					

			
				
				System.out.println(Arrays.toString(parts));
			}
		
				
				if (map.containsKey("price")) {
					String pricearch = map.get("price");
					String[] parts1 = pricearch.split(":");
					float fprice = Float.parseFloat(pricearch);

					if (parts[1].equalsIgnoreCase("all")) {
						
					}
					else if (parts[0].contentEquals("gt") ) {
						bson = gt("price", fprice);
					}}
						

				
					
					System.out.println(Arrays.toString(parts));
				}
		
			try (MongoClient mc = new MongoClient("localhost", 27017);) {
				
				MongoDatabase db = mc.getDatabase("stockdb");
//				MongoCollection<Document> collection = db.getCollection("stocks");
//				FindIterable<Document> list = collection.find(bson);// Alternate


				FindIterable<Document> list = db.getCollection("stocks").find(bson);  
//				FindIterable<Document> list = db.getCollection("stocks").find(bson).limit(5); // limited to 5				

				for (Document document : list) {
					String json = document.toJson();
					Stock stock = gson.fromJson(json, Stock.class);
					stocks.add(stock);
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				}		
			
			
			return stocks;
	}


}
