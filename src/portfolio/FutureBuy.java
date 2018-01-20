package portfolio;

import data.*;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;

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
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, TicketDoesNotExistException, RatioDoesNotExistException {
        Money pacted = this.priceShare.multiply(numShares);
        Money actual = stockExchange.value(ticket).multiply(numShares);
        if (pacted.getCurrency().equals(actual.getCurrency())) {
            return new Money (actual.getQuantity().subtract(pacted.getQuantity()), pacted.getCurrency());
        } else {
            Money changed = actual.change(moneyEx.exchangeRatio(currencyTo, actual.getCurrency()), currencyTo);
            return new Money (changed.getQuantity().subtract(pacted.getQuantity()), pacted.getCurrency());
        }
    }
}
