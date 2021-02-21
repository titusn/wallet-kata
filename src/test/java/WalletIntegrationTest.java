import com.titusnachbauer.wallet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletIntegrationTest {
    private final RateProvider iexRateProvider = new IEXRateProvider();
    private final Wallet wallet = new Wallet(iexRateProvider);

    @Test
    void givenWalletWithOneStockWhenCalledShouldReturnCurrentValue() {
        wallet.add(new Stock(2, "AAPL"));
        assertTrue(wallet.computeValue() > 0.0);
    }

    @Test
    void givenWalletWithUnknownStockTypeWhenCalledShouldThrowTickerSymbolUnknown() {
        wallet.add(new Stock(1, "THISISNOTATICKERSYMBOL"));
        Exception e = assertThrows(TickerSymbolNotFound.class, wallet::computeValue);
        assertEquals("Could not find this ticker symbol: THISISNOTATICKERSYMBOL", e.getMessage());
    }

    @Test
    void givenComplexWalletWhenCalledShouldReturnCurrentValue() {
        wallet.add(new Stock(140, "CE")); //Celanese
        wallet.add(new Stock(20, "GOOG")); //Alphabet Class C
        wallet.add(new Stock(40, "BRK.B")); //Berkshire Hathaway Class B
        double value = wallet.computeValue();
        assertTrue(value > 0.0);
        System.out.println(value);
    }
}
