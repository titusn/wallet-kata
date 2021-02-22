package com.titusnachbauer.wallet.provider;

import com.titusnachbauer.wallet.Stock;
import com.titusnachbauer.wallet.exception.CurrencyNotFound;
import com.titusnachbauer.wallet.exception.TickerSymbolNotFound;

import java.util.Currency;

public class MockRateProvider implements RateProvider {
    public static final double USD_EUR_EXCHANGE_RATE = 1.21;

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
            return value / USD_EUR_EXCHANGE_RATE;
        } else {
            throw new CurrencyNotFound(currency.getCurrencyCode());
        }
    }
}
