package com.titusnachbauer.wallet.rateprovider.iexservice;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

public class APIClient {
    public static final String BASE_URL = "https://cloud.iexapis.com/v1/";
    private final String publishToken;
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final IEXService iexService = retrofit.create(IEXService.class);

    public APIClient(String iexPublishToken) {
        publishToken = iexPublishToken;
    }

    public QuoteDto getQuote(String symbol) throws IOException {
        Response<QuoteDto> response = iexService.getQuote(symbol, publishToken).execute();
        return getResponseBody(response);
    }

    public StatusDto getAPIStatus() throws IOException {
        Response<StatusDto> response = iexService.getAPIStatus().execute();
        return getResponseBody(response);
    }

    private <T extends IEXDto> T getResponseBody(Response<T> response) throws IOException {
        if (response.code() == HTTP_OK) {
            return response.body();
        } else {
            throw new IOException("Server responded " + response.code());
        }
    }
}
