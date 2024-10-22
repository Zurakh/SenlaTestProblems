package org.example.javazero_2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CurrencyConverter {
    public final Map<Currency, BigDecimal> currencyRates;
    public final Set<String> currencyCodes;

    public CurrencyConverter(Map<Currency, BigDecimal> currencyRates) {
        this.currencyRates = currencyRates;
        currencyCodes = currencyRates.keySet()
                .stream()
                .map(Currency::getCurrencyCode)
                .collect(Collectors.toSet());
    }

    public BigDecimal convert(BigDecimal value, Currency from, Currency to) {
        // convert to dollars
        BigDecimal res;
        try {
            res = value.divide(currencyRates.get(from), 2, RoundingMode.HALF_UP);
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Can't convert " + from.getDisplayName());
        }

        // convert to targeted currency
        try {
            res = res.multiply(currencyRates.get(to));
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Can't convert " + to.getDisplayName());
        }
        return res;
    }

    public boolean isCurrencyAvailable(String currency) {
        return currencyRates.keySet()
                .stream()
                .map(Currency::getCurrencyCode)
                .anyMatch(Predicate.isEqual(currency));
    }

    public Map<Currency, BigDecimal> convertToEveryCurrency(BigDecimal value, Currency from) {
        if (!currencyCodes.contains(from.getCurrencyCode())) {
            System.out.println("Currency " + from + " is not available");
            return Collections.<Currency, BigDecimal>emptyMap();
        }

        var res = new HashMap<Currency, BigDecimal>(currencyRates.size());
        for (Currency to : currencyRates.keySet()) {
            BigDecimal cur_res = convert(value, from, to);
            res.put(to, cur_res);
        }
        return res;
    }
}
