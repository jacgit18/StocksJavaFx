package com.red.stocks.driver;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.red.stocks.dao.Water;

import static com.mongodb.client.model.Filters.*;

public class DriverStock {

	public static void main(String[] args) {
		// closing Data Base and prevent leaking example
		
		try (MongoClient mc = new MongoClient("localhost", 27017);
				Water water = new Water();) {

//		try (MongoClient mc = new MongoClient("localhost", 27017);) {
			
			
			MongoDatabase db = mc.getDatabase("stockdb");


//			int x = 3 / 0;
			
			Bson query = eq("symbol", "ibm-1"); // bson is binary version of json
			Bson query2 = gt("price", 100);

				
			
			// type wrong data type to equal to to change it quicker
			db.getCollection("stocks").find(query2).limit(5).forEach((Document d) -> System.out.println(d));

			// there are two types of forEach that is why you had to type out the for each
			// like this
			// also make sure mongo db is running when running code
			mc.close(); // will be on test
			System.out.println("close" + mc);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
