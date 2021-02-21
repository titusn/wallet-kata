package com.titusnachbauer.service;

import com.titusnachbauer.wallet.TickerSymbolNotFound;

public class QuoteService {

    public QuoteDto getQuoteOnSymbol(String symbol) {
        throw new TickerSymbolNotFound();
    }
}
