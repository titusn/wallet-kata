package com.titusnachbauer.wallet.exception;

public class CurrencyNotSet extends RuntimeException {
    public CurrencyNotSet(String message) {
        super(message);
    }
}
