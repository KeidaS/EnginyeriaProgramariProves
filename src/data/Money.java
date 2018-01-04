package data;

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
        if (other.currency.equals(this.currency)) {
            BigDecimal totalQuantity = this.quantity.add(other.quantity);
            Money result = new Money(totalQuantity, other.currency);
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public Money subtract (Money other) throws IllegalArgumentException{
        if (other.currency.equals(this.currency)) {
            BigDecimal totalQuantity = this.quantity.subtract(other.quantity);
            Money result = new Money(totalQuantity, other.currency);
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public Money multiply (int multiplier) {
        BigDecimal totalQuantity = this.quantity.multiply(BigDecimal.valueOf(multiplier));
        Money result = new Money(totalQuantity, this.currency);
        return result;
    }
    public Money change (BigDecimal ratio, Currency to) {
        BigDecimal totalQuantity = this.quantity.multiply(ratio);
        Money result = new Money(totalQuantity, to);
        return result;
    }
    public boolean equals (Object other) {
        if (other.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + quantity.hashCode();
        hash = hash * 17 + currency.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}