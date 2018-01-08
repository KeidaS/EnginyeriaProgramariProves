package portfolio;
import data.*;
import services.MoneyExchange;
import services.StockExchange;

public class Cash implements Investment {
    Money money;

    public Cash (Money money) {
        this.money = money;
    }

    @Override
    public Money evaluate(Currency currencyTo, MoneyExchange moneyEx, StockExchange stockExchange) throws EvaluationException {
        return null;
    }
}
