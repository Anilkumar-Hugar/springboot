package com.yahoofinance.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yahoofinance.entity.Stock;

import yahoofinance.YahooFinance;

@Service
public class StockService {
	@Autowired
	private RestTemplate restTemplate;
	public static final String url = "https://finance.yahoo.com/quote";

	public StockService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Stock getStock(String stockName) throws IOException {
		//String apiurl = url + "/" + stockName;
		//ResponseEntity<Stock> responseEntity = restTemplate.getForEntity(apiurl, Stock.class);
		//Stock stock = responseEntity.getBody();
		 yahoofinance.Stock stock = YahooFinance.get(stockName,true);
		return new Stock(stockName, stock.getName(), stock.getQuote(true).getPrice().toString());
	}
}
