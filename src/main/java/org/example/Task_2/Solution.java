package org.example.Task_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Supplier;

public class Solution {



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<Currency, BigDecimal> currencyRates = Map.of(
                Currency.getInstance("USD"),  BigDecimal.valueOf(1),
                Currency.getInstance("RUB"),  BigDecimal.valueOf(96.577369),
                Currency.getInstance("EUR"),  BigDecimal.valueOf(0.923477),
                Currency.getInstance("INR"),  BigDecimal.valueOf(84.075438),
                Currency.getInstance("JPY"),  BigDecimal.valueOf(84.075438),
                Currency.getInstance("CNY"),  BigDecimal.valueOf(84.075438)
        );

        CurrencyConverter converter = new CurrencyConverter(currencyRates);

        String command = "empty";

        while (!"quit".equals(command)) {
            System.out.print("> ");
            String[] inputLine = reader.readLine().split(" ");
            command = inputLine[0];
            switch (command) {
                case "available" -> converter.currencyRates.keySet()
                        .forEach(currency ->
                                System.out.println(
                                        currency.getCurrencyCode() + " " + currency.getDisplayName()));
                case "convert" -> {
                    if (inputLine.length < 4) {
                        System.out.println("Expected 3 arguments");
                        continue;
                    }
                    inputLine[2] = inputLine[2].toUpperCase();
                    if (!converter.currencyCodes.contains(inputLine[2])) {
                        System.out.println("Currency witch code " + inputLine[2] + " is not available");
                        continue;
                    }
                    inputLine[3] = inputLine[3].toUpperCase();
                    if (!converter.currencyCodes.contains(inputLine[3])) {
                        System.out.println("Currency witch code " + inputLine[3] + " is not available");
                        continue;
                    }
                    BigDecimal res = converter.convert(new BigDecimal(inputLine[1]),
                            Currency.getInstance(inputLine[2]),
                            Currency.getInstance(inputLine[3]));

                    System.out.println(res + " " + inputLine[3]);;
                }
                case "all" -> {
                    if (inputLine.length < 3) {
                        System.out.println("Expected 2 arguments");
                        continue;
                    }
                    inputLine[2] = inputLine[2].toUpperCase();
                    if (!converter.currencyCodes.contains(inputLine[2])) {
                        System.out.println("Currency witch code " + inputLine[2] + " is not available");
                        continue;
                    }

                    Map<Currency, BigDecimal> res = converter.convertToEveryCurrency(
                            new BigDecimal(inputLine[1]),
                            Currency.getInstance(inputLine[2]));
                    res.forEach((key, value) -> System.out.println(key + ": " + value));
                }
                case "help" -> {
                    System.out.println("help -- print this info");
                    System.out.println("convert <value> <from> <to> -- converts value of one currency to another");
                    System.out.println("all <amount> <current currency> -- converts to every currency");
                    System.out.println("available -- prints all available currencies");
                }
                case "quit" -> {
                    System.out.println("Quiting...");
                }
                default -> {
                    System.out.println("Unknown command");
                }
            }
        }



    }
}
