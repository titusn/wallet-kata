package com.titusnachbauer.wallet.provider;

import com.titusnachbauer.wallet.Stock;

import java.util.Currency;

public interface RateProvider {
    double getRate(Stock stock);
    double getRateIn(Currency currency, Stock stock);
}
