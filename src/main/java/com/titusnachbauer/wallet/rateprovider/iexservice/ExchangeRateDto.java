package com.titusnachbauer.wallet.rateprovider.iexservice;

import com.google.gson.annotations.SerializedName;

public class ExchangeRateDto implements Dto {
    @SerializedName("symbol")
    private final String symbol;

    @SerializedName("rate")
    private final double rate;

    public ExchangeRateDto(String symbol, double rate) {
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getRate() {
        return rate;
    }
}
