package com.titusnachbauer.wallet;

public class TickerSymbolNotFound extends RuntimeException {
    public TickerSymbolNotFound(String symbol) {
        super("Could not find this ticker symbol: " + symbol);
    }
}
