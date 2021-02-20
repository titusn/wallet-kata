package com.titusnachbauer.client;

import okhttp3.*;

import java.io.IOException;
import java.net.URL;

public class Client {
    public static final int HTTP_STATUS_OK = 200;
    private OkHttpClient httpClient = new OkHttpClient();

    public int getRequest(URL url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        return response.code();
    }
}
