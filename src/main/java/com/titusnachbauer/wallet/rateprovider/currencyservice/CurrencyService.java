package com.titusnachbauer.wallet.rateprovider.currencyservice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyService {
    @GET("latest")
    Call<ExchangeRateDto> getExchangeRate();
}
