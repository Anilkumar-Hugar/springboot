package com.yahoofinance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yahoofinance.service.StockService;

@RestController
@RequestMapping("/api/v1")
public class StockController {
	@Autowired
	private StockService stockService;

	@GetMapping("/getStock")
	public ResponseEntity<com.yahoofinance.entity.Stock> getStock(@RequestParam String stockName) throws IOException {
		return ResponseEntity.ok(stockService.getStock(stockName));
	}
}
