import com.titusnachbauer.wallet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletIntegrationTest {
    private RateProvider iexRateProvider = new IEXRateProvider();
    private Wallet wallet = new Wallet(iexRateProvider);

    @Test
    void givenWalletWithOneStockWhenCalledShouldReturnCurrentValue() {
        Wallet wallet = new Wallet(new Stock(2, "AAPL"), iexRateProvider);
        assertTrue(wallet.computeValue() > 0.0);
    }

    @Test
    void givenWalletWithUnknownStockTypeWhenCalledShouldThrowTickerSymbolUnknown() {
        wallet.add(new Stock(1, "THISISNOTATICKERSYMBOL"));
        assertThrows(TickerSymbolNotFound.class, wallet::computeValue);
    }

}
