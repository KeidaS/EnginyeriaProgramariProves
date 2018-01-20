package test;

import static org.junit.Assert.*;

import data.*;
import org.junit.Test;
import portfolio.*;
import services.*;

import java.math.BigDecimal;


public class FutureBuyTest {
    public class TicketValue implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            return new Money(new BigDecimal(5), new Currency("Euros"));
        }
    }
    public class IncorrectTicketValue implements StockExchange {
        @Override
        public Money value(Ticket ticket) throws TicketDoesNotExistException {
            throw new TicketDoesNotExistException("This ticket doesn't exist");
        }
    }
    public class CorrectRatio implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            return new BigDecimal("2.4");
        }
    }
    public class IncorrectRatio implements MoneyExchange {
        @Override
        public BigDecimal exchangeRatio(Currency from, Currency to) throws RatioDoesNotExistException {
            throw new RatioDoesNotExistException("This ratio doesn't exist");
        }
    }
    public class IncorrectInvestment implements Investment {
        @Override
        public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
            throw new EvaluationException("There was a problem during the evaluation");
        }
    }
    @Test
    public void TestCorrectEvaluateWithSameCurrencyAndPositiveBalance() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Ticket ticket = new Ticket ("Euros");
        StockExchange value = new TicketValue();
        Money priceShare = new Money (new BigDecimal(20), new Currency("Euros"));
        FutureBuy future = new FutureBuy(ticket, 5, priceShare);
        Money result = future.evaluate(null, null, value);
        assertEquals(new BigDecimal("-75.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void TestCorrectEvaluateWithSameCurrencyAndNegativeBalance() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Ticket ticket = new Ticket ("Euros");
        StockExchange value = new TicketValue();
        Money priceShare = new Money (new BigDecimal(3), new Currency("Euros"));
        FutureBuy future = new FutureBuy(ticket, 5, priceShare);
        Money result = future.evaluate(null, null, value);
        assertEquals(new BigDecimal("10.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void TestCorrectEvaluateWithDiferentCurrencyAndPositiveBalance() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Ticket ticket = new Ticket ("Euros");
        StockExchange value = new TicketValue();
        Money priceShare = new Money (new BigDecimal(20), new Currency("Dollars"));
        MoneyExchange ratio = new CorrectRatio();
        FutureBuy future = new FutureBuy(ticket, 5, priceShare);
        Money result = future.evaluate(priceShare.getCurrency(), ratio, value);
        assertEquals(new BigDecimal("-40.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
    @Test
    public void TestCorrectEvaluateWithDiferentCurrencyAndNegativeBalance() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Ticket ticket = new Ticket ("Euros");
        StockExchange value = new TicketValue();
        Money priceShare = new Money (new BigDecimal(3), new Currency("Dollars"));
        MoneyExchange ratio = new CorrectRatio();
        FutureBuy future = new FutureBuy(ticket, 5, priceShare);
        Money result = future.evaluate(priceShare.getCurrency(), ratio, value);
        assertEquals(new BigDecimal("45.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
    @Test (expected = TicketDoesNotExistException.class)
    public void TestTicketDoesNotExistWithSameCurrency() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Ticket ticket = new Ticket ("Euros");
        StockExchange value = new IncorrectTicketValue();
        Money priceShare = new Money (new BigDecimal(3), new Currency("Euros"));
        FutureBuy future = new FutureBuy(ticket, 5, priceShare);
        Money result = future.evaluate(null, null, value);
    }
}
