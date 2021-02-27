package com.titusnachbauer.wallet.domain;

import java.util.Currency;

public class Stock {
    private final String symbol;
    private int quantity;
    private Currency currency;

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

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Currency getCurrency() {
        if (currency == null) {
            if (getSymbol().endsWith("-NA") || getSymbol().endsWith("-GY") || getSymbol().endsWith("EUR")) {
                return Currency.getInstance("EUR");
            } else {
                return Currency.getInstance("USD");
            }
        }
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
