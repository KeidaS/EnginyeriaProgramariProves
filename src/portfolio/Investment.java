package portfolio;

import data.*;
import services.*;


public interface Investment {
    Money evaluate (Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException;
}
