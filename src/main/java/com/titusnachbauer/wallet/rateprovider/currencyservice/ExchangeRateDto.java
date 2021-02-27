package com.titusnachbauer.wallet.rateprovider.currencyservice;

import com.google.gson.annotations.SerializedName;
import com.titusnachbauer.wallet.exception.ExchangeRateUnknown;

import java.util.Currency;
import java.util.Map;

public class ExchangeRateDto implements CurrencyDto {
    @SerializedName("rates")
    private final Map<String, Double> rates;

    public ExchangeRateDto(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double getRate(Currency currency) {
        if (rates.containsKey(currency.getCurrencyCode())) {
            return rates.get(currency.getCurrencyCode());
        } else {
            throw new ExchangeRateUnknown(Currency.getInstance("EUR"), currency);
        }
    }
}
