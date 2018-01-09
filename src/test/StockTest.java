package test;

import data.*;
import portfolio.*;
import static org.junit.Assert.*;
import org.junit.Test;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

public class StockTest {
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
    public class CorrectTicketValue implements StockExchange {
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
    public class IncorrectInvestment implements Investment {
        @Override
        public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
            throw new EvaluationException("There was a problem during the evaluation");
        }
    }
    @Test
    public void testCorrectEvaluateWithoutMoneyExchange() throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {
        Stock stock = new Stock (new Ticket("CBK"), 3);
        CorrectTicketValue value = new CorrectTicketValue();
        Money result = stock.evaluate(new Currency("Dollars"), null, value);
        assertEquals(new BigDecimal(5), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testCorrectEvaluateWithMoneyExchange() throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {
        Stock stock = new Stock (new Ticket("CBK"), 3);
        CorrectTicketValue value = new CorrectTicketValue();
        CorrectRatio moneyEx = new CorrectRatio();
        Money result = stock.evaluate(new Currency ("Dollars"), moneyEx, value);
        assertEquals(new BigDecimal("12.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
    @Test(expected = TicketDoesNotExistException.class)
    public void testTicketDoesNotExistExceptionOnEvaluateWithoutMoneyExchange() throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {
        Stock stock = new Stock (new Ticket("CBK"), 3);
        IncorrectTicketValue value = new IncorrectTicketValue();
        Money result = stock.evaluate(new Currency("Dollars"), null, value);
    }
    @Test(expected = TicketDoesNotExistException.class)
    public void testTicketDoesNotExistExceptionOnEvaluateWithoMoneyExchange() throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {
        Stock stock = new Stock (new Ticket("CBK"), 3);
        IncorrectTicketValue value = new IncorrectTicketValue();
        CorrectRatio moneyEx = new CorrectRatio();
        Money result = stock.evaluate(new Currency("Dollars"), moneyEx, value);
    }
    @Test(expected = RatioDoesNotExistException.class)
    public void testRatioDoesNotExistException() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Stock stock = new Stock (new Ticket("CBK"), 3);
        CorrectTicketValue value = new CorrectTicketValue();
        IncorrectRatio moneyEx = new IncorrectRatio();
        Money result = stock.evaluate(new Currency("Dollars"), moneyEx, value);
    }
    @Test(expected = EvaluationException.class)
    public void testEvaluationExceptionDuringEvaluation() throws EvaluationException, RatioDoesNotExistException {
        CorrectTicketValue value = new CorrectTicketValue();
        IncorrectInvestment incorrect = new IncorrectInvestment();
        Money result = incorrect.evaluate(null, null, value);
    }
    
}