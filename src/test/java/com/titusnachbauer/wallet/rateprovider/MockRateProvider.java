package com.titusnachbauer.wallet.rateprovider;

import com.titusnachbauer.wallet.domain.Stock;
import com.titusnachbauer.wallet.exception.ExchangeRateUnknown;
import com.titusnachbauer.wallet.exception.TickerSymbolNotFound;

import java.util.Currency;

public class MockRateProvider implements RateProvider {
    public static final double EUR_USD_EXCHANGE_RATE = 1.21;

    @Override
    public double getRate(Stock stock) {
        switch (stock.getSymbol()) {
            case "STOCKWITHVALUEONE":
            case "STOCKWITHVALUEONEEUR":
                return 1.0;
            case "STOCKWITHVALUETWO":
            case "STOCKWITHVALUETWOEUR":
            case "STOCKWITHVALUETWOUSD":
                return 2.0;
            case "STOCKWITHVALUETHREE":
                return 3.0;
            default:
                throw new TickerSymbolNotFound(stock.getSymbol());
        }
    }

    @Override
    public double getRateIn(Currency currency, Stock stock) {
        return convertTo(currency, stock.getCurrency(), getRate(stock));
    }

    @Override
    public double convertTo(Currency to, Currency from, double value) {
        return value * getExchangeRate(to, from);
    }

    private double getExchangeRate(Currency to, Currency from) {
        if (to.equals(from)) {
            return 1.0;
        } else if (to.getCurrencyCode().equals("USD") && from.getCurrencyCode().equals("EUR")) {
            return EUR_USD_EXCHANGE_RATE;
        } else if (to.getCurrencyCode().equals("EUR") && from.getCurrencyCode().equals("USD")) {
            return 1 / EUR_USD_EXCHANGE_RATE;
        } else {
            throw new ExchangeRateUnknown(to, from);
        }
    }
}
