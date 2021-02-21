package com.titusnachbauer.wallet;

import java.util.Currency;

public class MockRateProvider implements RateProvider {
    @Override
    public double getRate(Stock stock) {
        switch (stock.getSymbol()) {
            case "STOCKWITHVALUEONE":
            case "STOCKWITHVALUEONEEUR":
                return 1.0;
            case "STOCKWITHVALUETWO":
            case "STOCKWITHVALUETWOEUR":
                return 2.0;
            case "STOCKWITHVALUETHREE":
                return 3.0;
            default:
                throw new TickerSymbolNotFound(stock.getSymbol());
        }
    }

    @Override
    public double getRateIn(Currency currency, Stock stock) {
        if (stock.getSymbol().endsWith(currency.getCurrencyCode())){
            return getRate(stock);
        } else {
            return convertTo(currency, getRate(stock));
        }
    }

    private double convertTo(Currency currency, double value) {
        if (currency.getCurrencyCode().equals("USD")) {
            return value / 1.21; //assume dollar -> eur for now
        } else {
            throw new CurrencyNotFound(currency.getCurrencyCode());
        }
    }
}
