package com.titusnachbauer.wallet.rateprovider.iexservice;

import com.titusnachbauer.wallet.domain.Stock;
import com.titusnachbauer.wallet.exception.ExchangeRateUnknown;
import com.titusnachbauer.wallet.exception.TickerSymbolNotFound;
import com.titusnachbauer.wallet.rateprovider.RateProvider;
import com.titusnachbauer.wallet.rateprovider.currencyservice.CurrencyAPIClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Currency;
import java.util.Objects;
import java.util.Properties;

public class IEXRateProvider implements RateProvider {
    private final IEXAPIClient iexAPIClient;
    private final CurrencyAPIClient currencyAPIClient = new CurrencyAPIClient();

    public IEXRateProvider() {
        var properties = new Properties();
        try (FileInputStream file = new FileInputStream("local.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        iexAPIClient = new IEXAPIClient(properties.getProperty("token"));
    }

    @Override
    public double getRate(Stock stock) {
        QuoteDto quote = null;
        try {
            quote = iexAPIClient.getQuote(stock.getSymbol());
        } catch (Exception e) {
            if (isMessagePageNotFound(e)) {
                throw new TickerSymbolNotFound(stock.getSymbol());
            } else {
                e.printStackTrace();
            }
        }
        return Objects.requireNonNull(quote).getLatestPrice();
    }

    @Override
    public double getRateIn(Currency currency, Stock stock) {
        if (currency.equals(stock.getCurrency())) {
            return getRate(stock);
        } else {
            return convertTo(currency, stock.getCurrency(), getRate(stock));
        }
    }

    @Override
    public double convertTo(Currency to, Currency from, double value) {
        double exchangeRate = getExchangeRate(from, to);
        return value * exchangeRate;
    }

    private double getExchangeRate(Currency from, Currency to) {
        double rate = 0;
        try {
             rate = currencyAPIClient.getExchangeRate(from, to);
        } catch (Exception e) {
            if (isMessagePageNotFound(e)) {
                throw new ExchangeRateUnknown(from, to);
            } else {
                e.printStackTrace();
            }
        }
        return rate;
    }

    private boolean isMessagePageNotFound(Exception e) {
        return e.getMessage().endsWith("404");
    }

}
