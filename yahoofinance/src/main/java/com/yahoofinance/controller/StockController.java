package com.yahoofinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yahoofinance.service.StockService;

import yahoofinance.Stock;

@RestController
@RequestMapping("/api/v1")
public class StockController {
	@Autowired
	private StockService stockService;

	@GetMapping("/getStock")
	public ResponseEntity<String> getStock(@RequestParam String stockName) {
		Stock stock = stockService.getStock(stockName);
		String name = stock.getQuote().getBid().toString();
		return ResponseEntity.ok(name);
	}
}
