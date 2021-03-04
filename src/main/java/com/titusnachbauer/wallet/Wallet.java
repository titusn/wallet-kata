package com.titusnachbauer.wallet;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

    private List<Stock> stocks = new ArrayList<>();

    public Wallet(Stock stock) {
        stocks.add(stock);
    }

    public Wallet() {
        stocks.add(new Stock(0, "EMPTY"));
    }

    public Wallet(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double value() {
        double sum = 0;
        for (Stock stock: stocks) {
            sum += stock.value();
        }
        return sum;
    }
}
