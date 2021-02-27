package com.titusnachbauer.wallet.rateprovider.iexservice;

import com.titusnachbauer.wallet.domain.Stock;
import com.titusnachbauer.wallet.exception.ExchangeRateUnknown;
import com.titusnachbauer.wallet.exception.TickerSymbolNotFound;
import com.titusnachbauer.wallet.rateprovider.RateProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Currency;
import java.util.Objects;
import java.util.Properties;

public class IEXRateProvider implements RateProvider {
    private final APIClient apiClient;

    public IEXRateProvider() {
        var properties = new Properties();
        try (FileInputStream file = new FileInputStream("local.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        apiClient = new APIClient(properties.getProperty("token"));
    }

    @Override
    public double getRate(Stock stock) {
        QuoteDto quote = null;
        try {
            quote = apiClient.getQuote(stock.getSymbol());
        } catch (IOException e) {
            if (e.getMessage().endsWith("404")) {
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
        ExchangeRateDto exchangeRateDto = null;
        try {
            exchangeRateDto = apiClient.getExchangeRate(from, to);
        } catch (IOException e) {
            if (e.getMessage().endsWith("404")) {
                throw new ExchangeRateUnknown(from, to);
            } else {
                e.printStackTrace();
            }
        }
        return Objects.requireNonNull(exchangeRateDto).getRate();
    }
}
