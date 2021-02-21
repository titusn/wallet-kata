package com.titusnachbauer.wallet;

import java.util.Currency;

public interface RateProvider {
    double getRate(Stock stock);
    double getRateIn(Currency currency, Stock stock);
}
