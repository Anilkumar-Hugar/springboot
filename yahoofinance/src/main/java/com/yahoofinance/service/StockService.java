package com.yahoofinance.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class StockService {
	public Stock getStock(String stockName) {
		Stock stock = null;
		try {
			stock = YahooFinance.get(stockName);
			stock.getQuote().getPrice();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
