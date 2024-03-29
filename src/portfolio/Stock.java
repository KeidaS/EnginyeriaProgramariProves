package portfolio;

import data.*;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

public class Stock implements Investment{
    Ticket ticket;
    int numShares;

    public Stock (Ticket ticket, int numShares) {
        this.ticket = ticket;
        this.numShares = numShares;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, TicketDoesNotExistException, RatioDoesNotExistException {
        Money value = stockExchange.value(ticket);
        if (moneyEx == null) {
            return value;
        } else {
            return value.change((moneyEx.exchangeRatio(value.getCurrency(), currencyTo)), currencyTo);
        }
    }
}
