package com.yahoo.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yahoo.entity.StockData;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/get")
	public StockData getStock(@RequestParam String symbol) throws IOException {
		
		//Stock stock = YahooFinance.get(symbol);
		 Stock stock = restTemplate.getForObject("https://finance.yahoo.com/quote?symbol="+symbol,Stock.class);
		 String response=restTemplate.getForObject("https://finance.yahoo.com/quote?symbol="+symbol,String.class);
		  ObjectMapper mapper=new ObjectMapper();
		  mapper.readValue("https://finance.yahoo.com/lookup", Stock.class);
		  System.out.println(stock);
		  System.out.println(response);
		 
		if (stock != null) {
			StockData stockData=new StockData();
			stockData.setSymbol(stock.getSymbol());
			stockData.setPrice(stock.getQuote().getPrice().doubleValue());
			return stockData;
		}
		return null;
	}
}
