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
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, TicketDoesNotExistException {
        Money value = stockExchange.value(ticket);
        return value.multiply(this.numShares);
    }
}
