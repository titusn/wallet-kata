import com.titusnachbauer.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Objects;

class APIClientTest {
    private final String BASE_URL = "https://cloud.iexapis.com/v1";
    Client client = new Client();

    @Test
    void givenStatusURLThenResponseShouldBeOK() throws Exception {
        Assertions.assertEquals(Client.HTTP_STATUS_OK, client.getRequest(new URL(BASE_URL +"/status")).code());
    }

    @Test
    void givenStatusURLThenResponseShoulBeJSON() throws Exception {
        String contentType = Objects.requireNonNull(
                Objects.requireNonNull(
                client.getRequest(new URL(BASE_URL + "/status")).body())
                .contentType())
                .toString();
        Assertions.assertEquals("application/json; charset=utf-8", contentType);
    }
}
