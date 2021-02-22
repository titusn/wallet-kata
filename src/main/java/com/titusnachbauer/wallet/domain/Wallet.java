package com.titusnachbauer.wallet.domain;

import com.titusnachbauer.wallet.rateprovider.RateProvider;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private final Map<String, Integer> stocks = new HashMap<>();
    private final RateProvider rateProvider;

    public Wallet(List<Stock> stocks, RateProvider rateProvider) {
        this(rateProvider);
        for (Stock stock: stocks) {
             this.stocks.put(stock.getSymbol(), stock.getQuantity());
         }
    }

    public Wallet(Stock stock, RateProvider rateProvider) {
        this(rateProvider);
        this.stocks.put(stock.getSymbol(), stock.getQuantity());
    }

    public Wallet(RateProvider rateProvider) {
        this.rateProvider = rateProvider;
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

    public double computeValue() {
        return stocks.entrySet()
                .stream()
                .parallel()
                .mapToDouble(
                        this::getValueForStock)
                .sum();
    }

    public double computeValue(Currency currency) {
        return stocks.entrySet()
                .stream()
                .parallel()
                .mapToDouble(
                        entry -> getValueForStockIn(currency, entry))
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

    private double getValueForStockIn(Currency currency, Map.Entry<String, Integer> e) {
        return rateProvider.getRateIn(
                currency,
                new Stock(
                        e.getValue(),
                        e.getKey()
                ))
                * e.getValue();
    }
}
