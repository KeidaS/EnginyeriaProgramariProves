package portfolio;
import data.*;
import services.*;

import java.math.BigDecimal;

public class Cash implements Investment {
    Money money;

    public Cash (Money money) {
        this.money = money;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException, RatioDoesNotExistException {
        return this.money.change((moneyEx.exchangeRatio(this.money.getCurrency(), currencyTo)), currencyTo);
    }
}
