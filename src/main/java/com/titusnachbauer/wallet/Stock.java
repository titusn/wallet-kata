package com.titusnachbauer.wallet;

public class Stock {
    private final String symbol;
    private int quantity;

    public Stock(int quantity, String symbol) {
        this.quantity = quantity;
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }
}
