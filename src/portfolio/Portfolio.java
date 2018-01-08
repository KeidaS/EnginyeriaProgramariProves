package portfolio;


import data.*;
import services.MoneyExchange;
import services.StockExchange;

public class Portfolio implements Investment{

    public Portfolio () {

    }
    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException {
        return null;
    }
}
