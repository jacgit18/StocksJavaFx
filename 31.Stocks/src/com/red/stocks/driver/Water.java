package com.red.stocks.driver;

import java.io.IOException;


public class Water implements java.io.Closeable {

	@Override
	public void close() throws IOException {
		System.out.println("We are closed");
	}

}
