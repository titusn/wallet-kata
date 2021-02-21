package com.titusnachbauer.wallet;

public class CurrencyNotFound extends RuntimeException {
    public CurrencyNotFound(String currencyCode) {
        super("Could not find currency with code: " + currencyCode);
    }
}
