package portfolio;


import data.*;
import services.MoneyExchange;
import services.RatioDoesNotExistException;
import services.StockExchange;
import services.TicketDoesNotExistException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Investment {
    public List<Investment> list = new ArrayList<>();

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException, TicketDoesNotExistException {
        Money result = new Money(new BigDecimal(0), currencyTo);
        Investment investment;
        int i = 0;
        for (i = 0; i < this.list.size(); i++) {
            investment = this.list.get(i);
            if (result.getCurrency().equals(currencyTo)) {
                result.add(investment.evaluate(currencyTo,moneyEx, stockExchange));
            } else {
                Money change = investment.evaluate(result.getCurrency(), moneyEx, stockExchange);
                change = change.change(moneyEx.exchangeRatio(change.getCurrency(), currencyTo), currencyTo);
                result = result.add(change);
            }
        }
        return result;
    }

    public void addInvestment(Investment investment) {
        this.list.add(investment);
    }
}