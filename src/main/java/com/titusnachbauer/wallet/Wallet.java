package com.titusnachbauer.wallet;

public class Wallet {
    private final Stock stock;

    public Wallet() {
        this.stock = null;
    }

    public Wallet(Stock stock) {
        this.stock = stock;
    }

    public double value() {
        if (stock == null) {
            return 0.0;
        } else {
            return computeValue();
        }
    }

    private double computeValue() {
        return 1.0;
    }
}
