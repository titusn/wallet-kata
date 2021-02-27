package com.titusnachbauer.wallet.domain;

import com.titusnachbauer.wallet.rateprovider.RateProvider;

import java.util.*;

public class Wallet {
    private List<Stock> stocks = new ArrayList<>();
    private final RateProvider rateProvider;

    public Wallet(List<Stock> stocks, RateProvider rateProvider) {
        this(rateProvider);
        this.stocks = stocks;
    }

    public Wallet(Stock stock, RateProvider rateProvider) {
        this(rateProvider);
        this.stocks.add(stock);
    }

    public Wallet(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public void add(Stock stock) {
        boolean found = false;
        for (Stock current: stocks) {
            if (current.getSymbol().equals(stock.getSymbol())) {
                current.addQuantity(stock.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            this.stocks.add(stock);
        }
    }

    public int getQuantity(String stocktype) {
        int quantity = 0;
        for (Stock stock: stocks) {
            if (stock.getSymbol().equals(stocktype)) {
                return stock.getQuantity();
            }
        }
        return quantity;
    }

    public double computeValue() {
        double sum = 0.0;
        for (Stock stock: stocks) {
            sum += getValueForStock(stock);
        }
        return sum;
    }

    public double computeValue(Currency currency) {
        double sum = 0.0;
        for (Stock stock: stocks) {
            sum += getValueForStockIn(currency, stock);
        }
        return sum;
    }

    private double getValueForStock(Stock stock) {
        return rateProvider.getRate(stock) * stock.getQuantity();
    }

    private double getValueForStockIn(Currency currency, Stock stock) {
        return rateProvider.getRateIn(currency, stock) * stock.getQuantity();
    }

    public List<Stock> entrySet() {
        return stocks;
    }
}
