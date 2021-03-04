package com.titusnachbauer.wallet;

public class Stock {
    private final double number;
    private final String symbol;

    public Stock(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public double value() {
        switch (getSymbol()) {
            case "EMPTY":
                return 0;
            case "STOCKWITHRATEONE":
                return number;
            case "STOCKWITHRATETWO":
                return number * 2;
            default:
                throw new SymbolNotFound();
        }
    }

    private String getSymbol() {
        return symbol;
    }
}
