package com.finance.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.finance.configuration.FinanceConfiguration;
import com.finance.entity.MetaData;
import com.finance.entity.StockData;
import com.finance.entity.StockPrice;
import com.google.gson.Gson;

@Service
public class FinanceService {
	private final FinanceConfiguration config;

	public FinanceService(FinanceConfiguration config) {
		this.config = config;
	}

	public String getStockPrice(String symbol, String function) throws IOException, InterruptedException {
		String apiKey = config.getKey();

		String apiUrl = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + "&apikey="
				+ apiKey;

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(apiUrl)).build();

		HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
		System.out.println(json);
		Gson gson = new Gson();
		StockPrice stockPrice = gson.fromJson(json, StockPrice.class);
		MetaData metaData = stockPrice.getMetaData();

		switch (function) {
		case "TIME_SERIES_DAILY_ADJUSTED":
			for (Map.Entry<String, StockData> entry : stockPrice.getStockdaily().entrySet()) {
				String date = entry.getKey();
				StockData stockData = entry.getValue();
				System.out.println(stockData);
			}
			break;
		case "TIME_SERIES_INTRADAY":
			for (Map.Entry<String, StockData> entry : stockPrice.getStockdaily().entrySet()) {
				String date = entry.getKey();
				StockData stockData = entry.getValue();
				System.out.println(stockData);
			}
			break;
		case "TIME_SERIES_WEEKLY":
			for (Map.Entry<String, StockData> entry : stockPrice.getStockweekly().entrySet()) {
				String date = entry.getKey();
				StockData stockData = entry.getValue();
				System.out.println(stockData);
			}
			break;
		case "TIME_SERIES_MONTHLY":
			for (Map.Entry<String, StockData> entry : stockPrice.getStockMonthly().entrySet()) {
				String date = entry.getKey();
				StockData stockData = entry.getValue();
				System.out.println(stockData);
			}
			break;
		}

		metaData.setSymbol(stockPrice.getMetaData().getSymbol());
		System.out.println(metaData);
		return "data has been sent";
	}
}
