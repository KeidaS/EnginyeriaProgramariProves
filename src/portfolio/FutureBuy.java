package portfolio;

import data.*;
import services.MoneyExchange;
import services.StockExchange;

public class FutureBuy implements Investment{
    Ticket ticket;
    int numShares;
    Money priceShare;

    public FutureBuy (Ticket ticket, int numShares, Money priceShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.priceShare = priceShare;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException {
        return null;
    }
}
