package com.titusnachbauer.service;

import com.google.gson.annotations.SerializedName;

public class QuoteDto implements Dto{
    @SerializedName("symbol")
    private final String symbol;

    @SerializedName("latestPrice")
    private final Double latestPrice;

    QuoteDto(String symbol, Double latestPrice) {
        this.symbol = symbol;
        this.latestPrice = latestPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getLatestPrice() {
        return latestPrice;
    }
}
