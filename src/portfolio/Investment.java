package portfolio;

import data.*;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;


public interface Investment {
    Money evaluate (Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException;
}
