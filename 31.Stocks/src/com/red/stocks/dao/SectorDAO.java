package com.red.stocks.dao;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.red.stocks.fxml.model.Sector;

public class SectorDAO implements IQuery<Sector> {
	private static List<Sector> list = new ArrayList<>();

	@Override
	public List<Sector> findAll() {
		if (list.size() > 0 ) {
			return list;
		}
		try (MongoClient mc =  new MongoClient("localhost", 27017);) {
				
				MongoDatabase db = mc.getDatabase("stockdb");
				
//				DistinctIterable<Sector> distinctCategory 
//				= db.getCollection("sectors").distinct("sector", Sector.class);	
//				
//				list = new ArrayList<>();
//				for (Sector category : distinctCategory) {
//					list.add(category);
//				}
				
				MongoCollection<Document> collection = db.getCollection("sectors");	
				 FindIterable<Document> stocks = collection.find();
				 

				    
				    for (Document document : stocks) {
						System.out.println(document);
					}		   
				
//				return list;
				
				
			}
			catch (Exception e){
				e.printStackTrace();
			}		return new ArrayList<>();
	}

}