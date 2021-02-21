import com.titusnachbauer.wallet.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalletTest {
    private RateProvider mockRateProvider = new MockRateProvider();
    private Wallet wallet = new Wallet(mockRateProvider);

    @Test
    void givenNewStockItShouldHaveQuantityAndStockType() {
        Stock stock = new Stock(4, "AAPL");
        assertEquals(4, stock.getQuantity());
        assertEquals("AAPL", stock.getSymbol());
    }

    @Test
    void givenNewWalletWhenEmptyThenValueShouldBeZero() {
        Wallet wallet = new Wallet(new ArrayList<>(), mockRateProvider);
        assertEquals(0.0, wallet.computeValue());
    }

    @Test
    void givenWalletContainsSingleStockWhenRateOfStockIsOneThenValueShouldBeOne() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHVALUEONE"), mockRateProvider);
        assertEquals(1.0, wallet.computeValue());
    }

    @Test
    void givenWalletContainsSingleStockWhenRateOfStockIsTwoThenValueShouldBeTwo() {
        Wallet wallet = new Wallet(new Stock(1, "STOCKWITHVALUETWO"), mockRateProvider);
        assertEquals(2.0, wallet.computeValue());
    }

    @Test
    void givenWalletContainsTwoStocksWhenRateOfStockIsTwoThenValueShouldBeFour() {
        Wallet wallet = new Wallet(new Stock(2, "STOCKWITHVALUETWO"), mockRateProvider);
        assertEquals(4.0, wallet.computeValue());
    }

    @Test
    void givenWalletContainsTwoStockTypesWhenRateOfFirstIsOneAndSecondStockIsTwoThenValueShouldBeThree() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "STOCKWITHVALUEONE"));
        stocks.add(new Stock(1, "STOCKWITHVALUETWO"));
        Wallet wallet = new Wallet(stocks, mockRateProvider);
        assertEquals(3.0, wallet.computeValue());
    }

    @Test
    void givenWalletContainsStocksWhenStockIsAddedThenValueShouldIncrease() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock(1, "STOCKWITHVALUEONE"));
        stocks.add(new Stock(1, "STOCKWITHVALUETWO"));
        Wallet wallet = new Wallet(stocks, mockRateProvider);
        wallet.add(new Stock(1, "STOCKWITHVALUETHREE"));
        assertEquals(6.0, wallet.computeValue());
    }

    @Test
    void givenUnknownSymbolWhenCalculatingValueThenShouldThrowTickerSymbolNotFound(){
        wallet.add(new Stock(1, "STOCKWITHVALUEONE"));
        wallet.add(new Stock(1, "STOCKWITHVALUETWO"));
        wallet.add(new Stock(1, "STOCKWITHVALUETHREE"));
        wallet.add(new Stock(1, "UNKNOWNSTOCK"));
        assertThrows(TickerSymbolNotFound.class, wallet::computeValue);
    }

    @Test
    void givenWalletContainsStocksShouldShowQuantityPerStocktype() {
        wallet.add(new Stock(2, "STOCKWITHVALUEONE"));
        wallet.add(new Stock(3, "STOCKWITHVALUETWO"));
        assertEquals(2, wallet.getQuantity("STOCKWITHVALUEONE"));
    }

    @Test
    void givenWalletContainsStocksWhenExistingStocktypeIsAddedQuantityShouldIncrease() {
        wallet.add(new Stock(2, "STOCKWITHVALUEONE"));
        wallet.add(new Stock(3, "STOCKWITHVALUETWO"));
        wallet.add(new Stock(3, "STOCKWITHVALUETWO"));
        assertEquals(6, wallet.getQuantity("STOCKWITHVALUETWO"));
    }

    @Test
    void givenWalletDoesNotContainStocktypeThenQuantityShouldBeZero() {
        wallet.add(new Stock(2, "STOCKWITHVALUEONE"));
        wallet.add(new Stock(3, "STOCKWITHVALUETWO"));
        assertEquals(0, wallet.getQuantity("THISISNOTINTHEWALLET"));
    }

    @Test
    void givenWalletWhenCurrencyEURProvidedShouldCalculateValueInEUR() {
        wallet.add(new Stock(5, "STOCKWITHVALUEONEEUR"));
        wallet.add(new Stock(5, "STOCKWITHVALUETWOEUR"));
        assertEquals(15.0, wallet.computeValue(Currency.getInstance("EUR")));
    }

}
