package com.titusnachbauer.client;

import com.titusnachbauer.service.QuoteDto;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;

public class Client {
    public static final int HTTP_STATUS_OK = 200;
    public static final String BASE_URL = "https://cloud.iexapis.com/v1/";
    private final String publishToken;
    private final OkHttpClient httpClient = new OkHttpClient();

    public Client(String iexPublishToken) {
        publishToken = iexPublishToken;
    }

    public Response getRequest(URL url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        return call.execute();
    }

    public QuoteDto getQuote(String symbol) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IEXService iexService = retrofit.create(IEXService.class);
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
}
