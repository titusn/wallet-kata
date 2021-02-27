package com.titusnachbauer.wallet.domain;

import com.titusnachbauer.wallet.exception.CurrencyNotSet;

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
            throw new CurrencyNotSet("No currency set for stock: " + getSymbol());
        }
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
