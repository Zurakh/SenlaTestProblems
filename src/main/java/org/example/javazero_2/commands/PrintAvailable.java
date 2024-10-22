package org.example.javazero_2.commands;

import org.example.javazero_2.CurrencyConverter;

import java.io.IOException;

public class PrintAvailable implements Command {
    private final CurrencyConverter converter;

    public PrintAvailable(CurrencyConverter converter) {
        this.converter = converter;
    }

    @Override
    public void execute() throws IOException {
        converter.currencyRates.keySet()
                .forEach(currency ->
                        System.out.println(
                                currency.getCurrencyCode() + " " + currency.getDisplayName()));
    }
}
