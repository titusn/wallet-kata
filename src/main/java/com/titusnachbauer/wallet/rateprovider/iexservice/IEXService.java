package com.titusnachbauer.wallet.rateprovider.iexservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IEXService {
    @GET("stock/{symbol}/quote")
    Call<QuoteDto> getQuote(@Path("symbol") String symbol, @Query("token") String token);

    @GET("fx/latest")
    Call<ExchangeRateDto> getExchangeRate(@Query("symbols") String fromTo, @Query("token") String token);

    @GET("status")
    Call<StatusDto> getAPIStatus();
}
