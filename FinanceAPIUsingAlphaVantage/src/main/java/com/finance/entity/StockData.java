package com.finance.entity;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockData {
	@SerializedName("1. open")
    private String open;

    @SerializedName("2. high")
    private String high;

    @SerializedName("3. low")
    private String low;

    @SerializedName("4. close")
    private String close;

    @SerializedName("5. adjusted close")
    private String adjustedClose;

    @SerializedName("6. volume")
    private String volume;

    @SerializedName("7. dividend amount")
    private String dividendAmount;

    @SerializedName("8. split coefficient")
    private String splitCoefficient;
}
