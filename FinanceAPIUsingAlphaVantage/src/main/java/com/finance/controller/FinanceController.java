package com.finance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.entity.StockPrice;
import com.finance.service.FinanceService;

@RestController
public class FinanceController {
	@Autowired
	private FinanceService financeService;

	@GetMapping()
	public ResponseEntity<String> getStockPrice(@RequestParam String symbol,@RequestParam String function) throws IOException, InterruptedException {
		String stockPrice = financeService.getStockPrice(symbol,function);
		return ResponseEntity.ok(stockPrice);
	}
}
