package com.titusnachbauer.wallet;

public interface RateProvider {
    double getRate(Stock stock);
}
