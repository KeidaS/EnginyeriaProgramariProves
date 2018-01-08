package test;

import data.*;
import static org.junit.Assert.*;
import org.junit.Test;
import services.MoneyExchange;
import services.RatioDoesNotExistException;

import java.math.BigDecimal;

public class CashTest {
    public class Ratio implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            return new BigDecimal("2.4");
        }
    }
}