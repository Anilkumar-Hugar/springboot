package com.finance.entity;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {
	@SerializedName("1. Information")
	private String information;

	@SerializedName("2. Symbol")
	private String symbol;

	@SerializedName("3. Last Refreshed")
	private String lastRefreshed;

	@SerializedName("4. Output Size")
	private String outputSize;

	@SerializedName("5. Time Zone")
	private String timeZone;
}
