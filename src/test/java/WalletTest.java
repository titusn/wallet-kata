import com.titusnachbauer.wallet.Stock;
import com.titusnachbauer.wallet.Wallet;
import kotlin.collections.ArrayDeque;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Wallet wallet = new Wallet(new ArrayList<>());
        assertEquals(0.0, wallet.value());
    }

    @Test
    void givenWalletContainsSingleStockWhenRateOfStockIsOneThenValueShouldBeOne() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHVALUEONE"));
        assertEquals(1.0, wallet.value());
    }

    @Test
    void givenWalletContainsSingleStockWhenRateOfStockIsTwoThenValueShouldBeTwo() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHVALUETWO"));
        assertEquals(2.0, wallet.value());
    }

    @Test
    void givenWalletContainsTwoStocksWhenRateOfStockIsTwoThenValueShouldBeFour() {
        Wallet wallet = new Wallet(new Stock(2, "STOCKWITHVALUETWO"));
        assertEquals(4.0, wallet.value());
    }

    @Test
    void givenWalletContainsTwoStockTypesWhenRateOfFirstIsOneAndSecondStockIsTwoThenValueShouldBeThree() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "STOCKWITHVALUEONE"));
        stocks.add(new Stock(1, "STOCKWITHVALUETWO"));
        Wallet wallet = new Wallet(stocks);
        assertEquals(3.0, wallet.value());
    }
}
