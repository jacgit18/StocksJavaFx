package com.red.stocks.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// Code From Video
public interface IQuery <T> {
	
	List <T> findAll();
	default List <T> findBy(Map<String,String> map){
		return new ArrayList<>();	
		
	}

	
	
	
	
}
