package portfolio;

import data.*;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;


public interface Investment {
    Money evaluate (Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException;
}
