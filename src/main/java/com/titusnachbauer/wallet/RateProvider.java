package com.titusnachbauer.wallet;

public class RateProvider {
    public double getRate(Stock stock) {
        switch (stock.getSymbol()) {
            case "STOCKWITHVALUEONE":
                return 1.0;
            case "STOCKWITHVALUETWO":
                return 2.0;
            case "STOCKWITHVALUETHREE":
                return 3.0;
            default:
                throw new TickerSymbolNotFound();
        }
    }
}
