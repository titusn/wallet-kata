package com.titusnachbauer.wallet.rateprovider;

import com.titusnachbauer.wallet.domain.Stock;
import com.titusnachbauer.wallet.domain.Wallet;

import java.util.Currency;

public class CurrencyFinder {
    private static final CurrencyFinder currencyFinder = new CurrencyFinder();
    private CurrencyFinder() {
    }

    public static CurrencyFinder getInstance() {
        return currencyFinder;
    }

    public void addCurrencies(Wallet wallet) {
        for (Stock stock:wallet.entrySet()) {
            stock.setCurrency(findCurrencyFor(stock.getSymbol()));
        }
    }

    private Currency findCurrencyFor(String symbol) {
        if (symbol.endsWith("-NA") || symbol.endsWith("-GY") || symbol.endsWith("EUR")) {
            return Currency.getInstance("EUR");
        } else {
            return Currency.getInstance("USD");
        }
    }
}
