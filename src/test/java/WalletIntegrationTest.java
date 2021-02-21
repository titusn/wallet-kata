import com.titusnachbauer.wallet.IEXRateProvider;
import com.titusnachbauer.wallet.RateProvider;
import com.titusnachbauer.wallet.Stock;
import com.titusnachbauer.wallet.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletIntegrationTest {
    private RateProvider iexRateProvider = new IEXRateProvider();
    private Wallet wallet = new Wallet(iexRateProvider);

    @Test
    void givenWalletWithOneStockWhenCalledShouldReturnCurrentValue() {
        Wallet wallet = new Wallet(new Stock(2, "AAPL"), iexRateProvider);
        assertTrue(wallet.computeValue() > 0.0);
    }

}
