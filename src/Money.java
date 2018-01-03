import java.math.BigDecimal;

public class Money {
    BigDecimal quantity;
    Currency currency;

    public Money(BigDecimal quantity, Currency currency) {
        this.quantity = quantity;
        this.currency = currency;
    }
    public Currency getCurrency() {
        return currency;
    }
    public Money add (Money other) throws  IllegalArgumentException{
        other.quantity = quantity.add(other.quantity);
        return other;
    }
    public Money subtract (Money other) throws IllegalArgumentException{
        other.quantity = quantity.subtract(other.quantity);
        return other;
    }
    public Money multiply (int multiplier) throws IllegalArgumentException{
        this.quantity = this.quantity.multiply(BigDecimal.valueOf(multiplier));
        Money result = new Money(quantity, this.currency);
        return result;
    }
}
