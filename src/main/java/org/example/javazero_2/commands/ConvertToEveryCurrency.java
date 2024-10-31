package org.example.javazero_2.commands;

import org.example.javazero_2.CurrencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class ConvertToEveryCurrency implements Command{
    private final BufferedReader reader;
    private final CurrencyConverter converter;

    public ConvertToEveryCurrency(BufferedReader reader, CurrencyConverter converter) {
        this.reader = reader;
        this.converter = converter;
    }

    @Override
    public void execute() throws IOException {
        System.out.print("Enter value: ");
        String valueStr = reader.readLine();

        if (!valueStr.chars().allMatch(Character::isDigit)) {
            System.out.println("Incorrect value");
            return;
        }

        BigDecimal value = new BigDecimal(valueStr);

        System.out.print("Enter currency convert from: ");
        String fromStr = reader.readLine().toUpperCase();
        if (!converter.currencyCodes.contains(fromStr)) {
            System.out.println("Currency witch code " + fromStr + " is not available");
            return;
        }

        Currency from = Currency.getInstance(fromStr);
        Map<Currency, BigDecimal> res = converter.convertToEveryCurrency(value, from);
        res.forEach((key, val) -> System.out.println(key + ": " + val));
    }
}
