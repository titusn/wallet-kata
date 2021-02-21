import com.titusnachbauer.client.Client;
import com.titusnachbauer.service.QuoteDto;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class APIClientTest {
    Properties properties = new Properties();
    Client client;

    APIClientTest() throws IOException {
        properties.load(new FileInputStream("local.properties"));
        client = new Client(properties.getProperty("token"));
    }

    @Test
    void givenInternetConnectivityAPIStatusShouldBeUp() throws Exception {
        assertEquals("up", client.getAPIStatus().getStatus());
    }

    @Test
    void givenExistingSymbolClientShouldReturnQuote() throws Exception {
        QuoteDto quote = client.getQuote("AAPL");
        assertNotNull(quote);
        assertEquals("AAPL", quote.getSymbol());
        assertTrue(quote.getLatestPrice() > 0.0);
    }

    @Test
    void givenNonExistingSymbolCliendShouldThrowExceptionWith404() {
        assertThrows(IOException.class, () -> client.getQuote("THISISNOTATICKERSYMBOL"));
    }
}
