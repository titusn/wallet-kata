package com.titusnachbauer.wallet.rateprovider.iexservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class APIClientIntegrationTest {
    Properties properties = new Properties();
    APIClient apiClient;

    APIClientIntegrationTest() throws IOException {
        properties.load(new FileInputStream("local.properties"));
        apiClient = new APIClient(properties.getProperty("token"));
    }

    @Test
    void givenInternetConnectivityAPIStatusShouldBeUp() throws Exception {
        assertEquals("up", apiClient.getAPIStatus().getStatus());
    }

    @Test
    void givenExistingSymbolClientShouldReturnQuote() throws Exception {
        QuoteDto quote = apiClient.getQuote("AAPL");
        assertNotNull(quote);
        assertEquals("AAPL", quote.getSymbol());
        assertTrue(quote.getLatestPrice() > 0.0);
    }

    @Test
    void givenNonExistingSymbolCliendShouldThrowExceptionWith404() {
        Exception exeption = assertThrows(IOException.class, () -> apiClient.getQuote("THISISNOTATICKERSYMBOL"));
        Assertions.assertTrue(exeption.getMessage().endsWith("404"));
    }
}
