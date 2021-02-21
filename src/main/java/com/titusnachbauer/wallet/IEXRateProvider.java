package com.titusnachbauer.wallet;

import com.titusnachbauer.client.Client;
import com.titusnachbauer.service.QuoteDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class IEXRateProvider implements RateProvider{
    private final Client client;
    private Properties properties = new Properties();

    public IEXRateProvider() {
        try (FileInputStream file = new FileInputStream("local.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new Client(properties.getProperty("token"));
    }

    @Override
    public double getRate(Stock stock) {
        QuoteDto quote= null;
        try {
            quote = client.getQuote(stock.getSymbol());
        } catch (IOException e) {
            if (e.getMessage().endsWith("404")) {
                throw new TickerSymbolNotFound();
            } else {
                e.printStackTrace();
            }
        }
        return Objects.requireNonNull(quote).getLatestPrice();
    }
}
