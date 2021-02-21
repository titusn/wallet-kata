package com.titusnachbauer.client;

import com.titusnachbauer.service.QuoteDto;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Client {
    public static final int HTTP_STATUS_OK = 200;
    public static final String BASE_URL = "https://cloud.iexapis.com/v1/";
    private final String publishToken;
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final IEXService iexService = retrofit.create(IEXService.class);

    public Client(String iexPublishToken) {
        publishToken = iexPublishToken;
    }

    public QuoteDto getQuote(String symbol) throws IOException {
        try {
            retrofit2.Response<QuoteDto> response = iexService.getQuote(symbol, publishToken).execute();
            if (response.code() == HTTP_STATUS_OK) {
                return response.body();
            } else {
                throw new IOException("Server responded " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public StatusDto getAPIStatus() throws IOException {
        try {
            retrofit2.Response<StatusDto> response = iexService.getAPIStatus().execute();
            if (response.code() == HTTP_STATUS_OK) {
                return response.body();
            } else {
                throw new IOException("Server responded " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
