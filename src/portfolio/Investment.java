package portfolio;

import data.*;
import services.MoneyExchange;
import services.StockExchange;


public interface Investment {
    Money evaluate (Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException;
}
