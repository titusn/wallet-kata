package com.titusnachbauer.wallet;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private List<Stock> stocks = new ArrayList<>();
    private RateProvider rateProvider = new RateProvider();

    public Wallet(List<Stock> stocks) {
        if (stocks != null) {
            this.stocks = stocks;
        }
    }

    public Wallet(Stock stock) {
        this.stocks.add(stock);
    }

    public double value() {
        return computeValue();
    }

    private double computeValue() {
        double value = 0.0;
        for (Stock stock: stocks) {
            value += stock.getQuantity() * rateProvider.getRate(stock);
        }
        return value;
    }

    public void add(Stock stock) {
        stocks.add(stock);
    }
}
