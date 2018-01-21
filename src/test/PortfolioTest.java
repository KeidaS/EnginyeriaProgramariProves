package test;

import data.*;
import org.junit.Test;
import portfolio.*;
import services.*;
import static org.junit.Assert.*;
import java.math.BigDecimal;



public class PortfolioTest {
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
    public class CorrectInvestmentEuros implements Investment {
        @Override
        public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
            return new Money(new BigDecimal("40.53"), new Currency("Euros"));
        }
    }
    public class CorrectInvestmentDollars implements Investment {
        @Override
        public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
            return new Money(new BigDecimal("30.53"), new Currency("Dollars"));
        }
    }
    public class IncorrectInvestment implements Investment {
        @Override
        public Money evaluate (Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException {
            throw new EvaluationException("There was a problem during the evaluation");
        }
    }
    @Test
    public void TestCorrectTotalAddOnPortfolioWithSameCurrency() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Portfolio portfolio = new Portfolio();
        portfolio.addInvestment(new CorrectInvestmentEuros());
        portfolio.addInvestment(new CorrectInvestmentEuros());
        Currency currency = new Currency("Euros");
        Money result = portfolio.evaluate(currency, null, null);
        assertEquals("81.06", result.getQuantity().toString());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void TestCorrectTotalAddOnPortfolioWithDiferentCurrency() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Portfolio portfolio = new Portfolio();
        portfolio.addInvestment(new CorrectInvestmentEuros());
        portfolio.addInvestment(new CorrectInvestmentDollars());
        MoneyExchange moneyEx = new CorrectRatio();
        Money result = portfolio.evaluate(new Currency("Euros"),moneyEx, null);
        assertEquals("113.81", result.getQuantity().toString());
        assertEquals("Euros", result.getCurrency().toString());
    }

    @Test (expected = RatioDoesNotExistException.class)
    public void TestRatioDoesNotExistOnPortfolioWithDiferentCurrency() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Portfolio portfolio = new Portfolio();
        portfolio.addInvestment(new CorrectInvestmentEuros());
        portfolio.addInvestment(new CorrectInvestmentDollars());
        MoneyExchange moneyEx = new IncorrectRatio();
        Money result = portfolio.evaluate(new Currency("Euros"),moneyEx, null);
    }
    @Test (expected = EvaluationException.class)
    public void TestEvaluationExceptionOnPortfolio() throws TicketDoesNotExistException, RatioDoesNotExistException, EvaluationException {
        Portfolio portfolio = new Portfolio();
        portfolio.addInvestment(new CorrectInvestmentEuros());
        portfolio.addInvestment(new IncorrectInvestment());
        Money result = portfolio.evaluate(new Currency("Euros"),null, null);
    }
}
