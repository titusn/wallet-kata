import com.titusnachbauer.wallet.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalletTest {
    @Test
    void stockShouldHaveSymbolAndValue() {
        Stock stock = new Stock("AAPL", 10);
        Assertions.assertEquals("AAPL", stock.getSymbol());
        Assertions.assertEquals(10, stock.getValue());
    }

}
