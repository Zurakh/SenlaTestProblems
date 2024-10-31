package org.example.javazero_2.commands;

import org.example.javazero_2.CurrencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class ConvertToSpecific implements Command{
    private final BufferedReader reader;
    private final CurrencyConverter converter;

    public ConvertToSpecific(BufferedReader reader, CurrencyConverter converter) {
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

        System.out.print("Enter currency converting from: ");
        String fromStr = reader.readLine().toUpperCase();
        if (!converter.currencyCodes.contains(fromStr)) {
            System.out.println("Currency witch code " + fromStr + " is not available");
            return;
        }

        System.out.print("Enter currency converting to: ");
        String toStr = reader.readLine().toUpperCase();
        if (!converter.currencyCodes.contains(toStr)) {
            System.out.println("Currency witch code " + fromStr + " is not available");
            return;
        }

        Currency from = Currency.getInstance(fromStr);
        Currency to = Currency.getInstance(toStr);
        BigDecimal res = converter.convert(value, from, to);
        System.out.println(res + " " + to);


    }
}
