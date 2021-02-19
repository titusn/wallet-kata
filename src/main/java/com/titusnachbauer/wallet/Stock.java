package com.titusnachbauer.wallet;

public class Stock {
    private final String symbol;
    private final double value;

    public Stock(String symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }
    public double getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }
}
