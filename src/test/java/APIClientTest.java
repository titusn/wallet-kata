import com.titusnachbauer.client.Client;
import com.titusnachbauer.service.QuoteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
        Assertions.assertEquals(Client.HTTP_STATUS_OK, client.getRequest(new URL(Client.BASE_URL +"/status")).code());
    }

    @Test
    void givenStatusURLThenClientResponseShouldBeJSON() throws Exception {
        String contentType = Objects.requireNonNull(
                Objects.requireNonNull(
                client.getRequest(new URL(Client.BASE_URL + "/status")).body())
                .contentType())
                .toString();
        Assertions.assertEquals("application/json; charset=utf-8", contentType);
    }

    @Test
    void givenExistingSymbolClientShouldReturnQuote() throws Exception {
        QuoteDto quote = client.getQuote("AAPL");
        Assertions.assertNotNull(quote);
        Assertions.assertEquals("AAPL", quote.getSymbol());
        Assertions.assertTrue(quote.getLatestPrice() > 0.0);
    }
}
