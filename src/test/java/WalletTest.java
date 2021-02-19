import com.titusnachbauer.wallet.Stock;
import com.titusnachbauer.wallet.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletTest {
    @Test
    void givenNewStockItShouldHaveQuantityAndStockType() {
        Stock stock = new Stock(4, "AAPL");
        assertEquals(4, stock.getQuantity());
        assertEquals("AAPL", stock.getSymbol());
    }

    @Test
    void givenNewWalletWhenEmptyThenValueShouldBeZero() {
        Wallet wallet = new Wallet();
        assertEquals(0.0, wallet.value());
    }

    @Test
    void givenWalletContainsSingleStockWhenRateOfStockIsOneThenValueShouldBeOne() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHVALUEONE"));
        assertEquals(1.0, wallet.value());
    }
}
