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

    public Wallet() {
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

    public void add(Stock newStock) {
        for (Stock currentStock: stocks) {
            if (currentStock.getSymbol().equals(newStock.getSymbol())) {
                currentStock.addQuantity(newStock.getQuantity());
            }
        }
        stocks.add(newStock);
    }

    public int getQuantity(String stocktype) {
        for (Stock stock: stocks) {
            if (stock.getSymbol().equals(stocktype)) {
                return stock.getQuantity();
            }
        }
        return 0;
    }
}
