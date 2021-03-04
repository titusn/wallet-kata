package com.titusnachbauer.wallet.rateprovider;

import com.titusnachbauer.wallet.rateprovider.currencyservice.CurrencyAPIClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Currency;

class CurrencyAPIClientTest {
    private final CurrencyAPIClient currencyAPIClient = new CurrencyAPIClient();

    @Test
    void givenCurrencyShouldReturnRate() throws Exception {
        double rate;
        rate = currencyAPIClient.getExchangeRate(Currency.getInstance("EUR"), Currency.getInstance("USD"));
        Assertions.assertNotEquals(0.0, rate);
    }
}
