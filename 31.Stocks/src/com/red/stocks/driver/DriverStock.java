package com.red.stocks.driver;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

public class DriverStock {

	public static void main(String[] args) {

		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("stockdb");
		
		Bson query = eq("symbol", "ibm-1");
		
		
		//type wrong data type to equal to to change it quicker 
		db.getCollection("stocks").find(query).forEach((Document d) -> System.out.println(d));
		
		// there are two types of forEach that is why you had to type out the for each like 
		// this
		
		// also make sure mongo db is running when running code
		
	}

}
