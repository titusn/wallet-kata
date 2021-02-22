package com.titusnachbauer.wallet.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IEXService {
    @GET("stock/{symbol}/quote")
    Call<QuoteDto> getQuote(@Path("symbol") String symbol, @Query("token") String token);

    @GET("status")
    Call<StatusDto> getAPIStatus();
}
