package com.titusnachbauer.wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private final Map<String, Integer> stocks = new HashMap<>();
    private final RateProvider rateProvider = new MockRateProvider();

    public Wallet(List<Stock> stocks) {
         for (Stock stock: stocks) {
             this.stocks.put(stock.getSymbol(), stock.getQuantity());
         }
    }

    public Wallet(Stock stock) {
        this.stocks.put(stock.getSymbol(), stock.getQuantity());
    }

    public Wallet() {
    }

    public double computeValue() {
        return stocks.entrySet()
                .stream()
                .parallel()
                .mapToDouble(
                        this::getValueForStock)
                .sum();

    }

    private double getValueForStock(Map.Entry<String, Integer> e) {
        return rateProvider.getRate(
                new Stock(
                        e.getValue(),
                        e.getKey()
                ))
                * e.getValue();
    }

    public void add(Stock stock) {
        Integer currentQuantity;
        currentQuantity = this.stocks.putIfAbsent(stock.getSymbol(), stock.getQuantity());
        if (currentQuantity != null) {
            this.stocks.put(stock.getSymbol(), stock.getQuantity() + currentQuantity);
        }
    }

    public int getQuantity(String stocktype) {
        int quantity = 0;
        if (stocks.containsKey(stocktype)) {
            quantity = stocks.get(stocktype);
        }
        return quantity;
    }

}
