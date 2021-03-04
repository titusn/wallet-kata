import com.titusnachbauer.wallet.Stock;
import com.titusnachbauer.wallet.Wallet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void givenWalletIsEmptyValueShouldBeZero() {
        Wallet wallet = new Wallet();
        assertEquals(0, wallet.value());
    }

    @Test
    void givenWalletContainsStockWithRate1ValueShouldBe1() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHRATEONE"));
        assertEquals(1, wallet.value());
    }

    @Test
    void givenWalletContains5StocksWithRate1ValueShouldBe5() {
        Wallet wallet = new Wallet(new Stock(5, "STOCKWITHRATEONE"));
        assertEquals(5, wallet.value());
    }

    @Test
    void givenWalletContainsMultipleStocksWithDifferentRatesValueShouldBeSumTotal() {
        Wallet wallet = new Wallet(
                new ArrayList<>(List.of(
                        new Stock(1, "STOCKWITHRATEONE"),
                        new Stock(1, "STOCKWITHRATETWO")
                )));
        assertEquals(3, wallet.value());
    }

}
