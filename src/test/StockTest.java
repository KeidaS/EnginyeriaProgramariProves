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
    public void testCorrectEvaluate() throws EvaluationException, RatioDoesNotExistException {

    }
    @Test
    public void testCorrectEvaluate2() throws EvaluationException, RatioDoesNotExistException {
        Money money = new Money (new BigDecimal(15),new Currency("Euros"));
        Cash cash = new Cash (money);
        CashTest.CorrectRatio moneyEx = new CashTest.CorrectRatio();
        Money result = cash.evaluate(new Currency ("Dollars"), moneyEx, null);
        assertEquals(new BigDecimal("36.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
}