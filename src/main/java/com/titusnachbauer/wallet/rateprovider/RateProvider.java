package com.titusnachbauer.wallet.rateprovider;

import com.titusnachbauer.wallet.domain.Stock;

import java.util.Currency;

public interface RateProvider {
    double getRate(Stock stock);
    double getRateIn(Currency currency, Stock stock);
    double convertTo(Currency currency, double value);
}
