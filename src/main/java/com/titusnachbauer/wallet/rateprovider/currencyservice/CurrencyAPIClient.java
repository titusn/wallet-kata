package com.titusnachbauer.wallet.rateprovider.currencyservice;

import com.titusnachbauer.wallet.exception.ExchangeRateUnknown;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Currency;

import static java.net.HttpURLConnection.HTTP_OK;

public class CurrencyAPIClient {
    public static final String BASE_URL = "https://api.exchangeratesapi.io/";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final CurrencyService currencyService = retrofit.create(CurrencyService.class);

    public CurrencyAPIClient() {
        //no setup needed, API is free to use
    }

    private <T extends CurrencyDto> T getResponseBody(Response<T> response) throws IOException {
        if (response.code() == HTTP_OK) {
            return response.body();
        } else {
            throw new IOException("Server responded " + response.code());
        }
    }

    public double getExchangeRate(Currency from, Currency to) throws IOException {
        Response<ExchangeRateDto> response = currencyService.getExchangeRate().execute();
        if (from.getCurrencyCode().equals("EUR")) {
            return getResponseBody(response).getRate(to);
        } else if (to.getCurrencyCode().equals("EUR")) {
            return 1 / getResponseBody(response).getRate(from);
        }
        throw new ExchangeRateUnknown(to, from);
    }
}
