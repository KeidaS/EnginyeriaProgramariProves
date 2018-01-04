package services;

import data.Currency;

import java.math.BigDecimal;

public interface MoneyExchange {
    BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException;
}

