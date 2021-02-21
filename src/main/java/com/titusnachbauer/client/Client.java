package com.titusnachbauer.client;

import com.titusnachbauer.service.QuoteDto;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;

public class Client {
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
            Response<QuoteDto> response = iexService.getQuote(symbol, publishToken).execute();
            return getResponseBody(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public StatusDto getAPIStatus() throws IOException {
        try {
            Response<StatusDto> response = iexService.getAPIStatus().execute();
            return getResponseBody(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Nullable
    private <T> T getResponseBody(Response<T> response) throws IOException {
        if (response.code() == HTTP_OK) {
            return response.body();
        } else {
            throw new IOException("Server responded " + response.code());
        }
    }
}
