package test;

import data.*;
import portfolio.*;
import static org.junit.Assert.*;
import org.junit.Test;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;

import java.math.BigDecimal;

public class CashTest {
    public class CorrectRatio implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            return new BigDecimal("2.4");
        }
    }
    public class IncorrectRatio implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            throw new RatioDoesNotExistException("The ratio doesn't exist");
        }
    }
    public class IncorrectInvestment implements Investment {
        @Override
        public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
            throw new EvaluationException("There was a problem during the evaluation");
        }
    }
    @Test
    public void testCorrectEvaluate() throws EvaluationException, RatioDoesNotExistException {
        Money money = new Money (new BigDecimal(15),new Currency("Euros"));
        Cash cash = new Cash (money);
        CorrectRatio moneyEx = new CorrectRatio();
        Money result = cash.evaluate(new Currency ("Dollars"), moneyEx, null);
        assertEquals(new BigDecimal("36.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
    @Test(expected = RatioDoesNotExistException.class)
    public void testRatioDoesNotExistExceptionOnEvaluate() throws EvaluationException, RatioDoesNotExistException {
        Money money = new Money (new BigDecimal(15),new Currency("Euros"));
        Cash cash = new Cash (money);
        IncorrectRatio moneyEx = new IncorrectRatio();
        Money result = cash.evaluate(new Currency ("Dollars"), moneyEx, null);
    }
    @Test(expected = EvaluationException.class)
    public void testEvaluationExceptionDuringEvaluation() throws EvaluationException, RatioDoesNotExistException {
        Money money = new Money (new BigDecimal(15),new Currency("Euros"));
        Cash cash = new Cash (money);
        CorrectRatio moneyEx = new CorrectRatio();
        IncorrectInvestment incorrect = new IncorrectInvestment();
        Money result = incorrect.evaluate((new Currency ("Dollars")), moneyEx, null);
    }
}