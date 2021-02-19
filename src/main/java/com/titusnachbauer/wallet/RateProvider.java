package com.titusnachbauer.wallet;

public class RateProvider {
    public double getRate(Stock stock) {
        if (stock.getSymbol().equals("STOCKWITHVALUEONE")) {
            return 1.0;
        } else if (stock.getSymbol().equals("STOCKWITHVALUETWO")) {
            return 2.0;
        }
        throw new TickerSymbolNotFound();
    }
}
