package portfolio;

import data.*;
import services.MoneyExchange;
import services.StockExchange;

public class Stock implements Investment{
    Ticket ticket;
    int numShares;

    public Stock (Ticket ticket, int numShares) {
        this.ticket = ticket;
        this.numShares = numShares;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException {
        return null;
    }
}
