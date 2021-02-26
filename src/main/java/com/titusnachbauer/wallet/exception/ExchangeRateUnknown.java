package com.titusnachbauer.wallet.exception;

import java.util.Currency;

public class ExchangeRateUnknown extends RuntimeException {
    public ExchangeRateUnknown(Currency from, Currency to) {
        super("Could not find exchange rate for: " + from + " -> " + to);
    }
}
