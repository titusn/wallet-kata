import com.titusnachbauer.client.Client;
import com.titusnachbauer.service.QuoteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class APIClientTest {
    Properties properties = new Properties();
    Client client;

    APIClientTest() throws IOException {
        properties.load(new FileInputStream("local.properties"));
        client = new Client(properties.getProperty("token"));
    }

    @Test
    void givenStatusURLThenClientResponseShouldBeOK() throws Exception {
        Assertions.assertEquals("up", client.getAPIStatus().getStatus());
    }

    @Test
    void givenExistingSymbolClientShouldReturnQuote() throws Exception {
        QuoteDto quote = client.getQuote("AAPL");
        Assertions.assertNotNull(quote);
        Assertions.assertEquals("AAPL", quote.getSymbol());
        Assertions.assertTrue(quote.getLatestPrice() > 0.0);
    }
}
