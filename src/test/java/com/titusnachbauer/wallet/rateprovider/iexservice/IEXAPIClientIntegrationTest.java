package com.titusnachbauer.wallet.rateprovider.iexservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class IEXAPIClientIntegrationTest {
    Properties properties = new Properties();
    IEXAPIClient iexAPIClient;

    IEXAPIClientIntegrationTest() throws IOException {
        properties.load(new FileInputStream("local.properties"));
        iexAPIClient = new IEXAPIClient(properties.getProperty("token"));
    }

    @Test
    void givenInternetConnectivityAPIStatusShouldBeUp() throws Exception {
        assertEquals("up", iexAPIClient.getAPIStatus().getStatus());
    }

    @Test
    void givenExistingSymbolClientShouldReturnQuote() throws Exception {
        QuoteDto quote = iexAPIClient.getQuote("AAPL");
        assertNotNull(quote);
        assertEquals("AAPL", quote.getSymbol());
        assertTrue(quote.getLatestPrice() > 0.0);
    }

    @Test
    void givenNonExistingSymbolCliendShouldThrowExceptionWith404() {
        Exception exeption = assertThrows(IOException.class, () -> iexAPIClient.getQuote("THISISNOTATICKERSYMBOL"));
        Assertions.assertTrue(exeption.getMessage().endsWith("404"));
    }
}
