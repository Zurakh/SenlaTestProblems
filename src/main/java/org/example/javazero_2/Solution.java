package org.example.javazero_2;

import org.example.javazero_2.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

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

        // lookup table with strings as keys and class instances with single method
        // Not sure is it more preferable than switch
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new Help());
        commandMap.put("all", new ConvertToEveryCurrency(reader, converter));
        commandMap.put("convert", new ConvertToSpecific(reader, converter));
        commandMap.put("available", new PrintAvailable(converter));
        commandMap.put("quit", new Command() {
            @Override
            public void execute() throws IOException {
                System.out.println("Quiting...");
            }
        });

        System.out.println("Type \"help\" to get help");
        String operaton = "";

        while (!"quit".equals(operaton)) {
            System.out.print("> ");
            String[] inputLine = reader.readLine().split(" ");
            operaton = inputLine[0];
            Command command = commandMap.get(operaton);
            if (command == null) {
                System.out.println("Command not found");
                continue;
            }
            command.execute();
        }



    }
}
