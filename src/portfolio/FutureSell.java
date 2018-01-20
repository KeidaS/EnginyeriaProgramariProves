package portfolio;


import data.*;
import services.*;

public class FutureSell implements Investment{
    Ticket ticket;
    int numShares;
    Money priceShare;

    public FutureSell (Ticket ticket, int numShares, Money priceShare) {
        this.ticket = ticket;
        this.numShares = numShares;
        this.priceShare = priceShare;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, TicketDoesNotExistException, RatioDoesNotExistException {
        Money pacted = this.priceShare.multiply(numShares);
        Money actual = stockExchange.value(ticket).multiply(numShares);
        if (pacted.getCurrency().equals(actual.getCurrency())) {
            return new Money (pacted.getQuantity().subtract(actual.getQuantity()), pacted.getCurrency());
        } else {
            Money exchanged = actual.change(moneyEx.exchangeRatio(currencyTo, actual.getCurrency()), currencyTo);
            return new Money (pacted.getQuantity().subtract(exchanged.getQuantity()), pacted.getCurrency());
        }
    }
}
