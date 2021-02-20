import com.titusnachbauer.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

class APIClientTest {
    @Test
    void givenStatusURLThenResponseShouldBeOK() throws IOException {
        Client client = new Client();
        Assertions.assertEquals(Client.HTTP_STATUS_OK, client.getRequest(new URL("https://cloud.iexapis.com/v1/status")));
    }
}
