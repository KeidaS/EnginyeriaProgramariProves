package test;

import data.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    public void testCorrectAddWithoutDecimals() {
        Money money1 = new Money (new BigDecimal(15),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal(25),new Currency("Euros"));
        Money result = money1.add(money2);
        assertEquals(new BigDecimal("40.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testCorrectAddWithDecimals() {
        Money money1 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal("25.50"),new Currency("Euros"));
        Money result = money1.add(money2);
        assertEquals(new BigDecimal("40.82"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddWithDiferentCurrency() {
        Money money1 = new Money (new BigDecimal(15.00),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal(25.00),new Currency("Eur"));
        Money result = money1.add(money2);
    }



    @Test
    public void testCorrectSubstractWithoutDecimalsWithPositiveResult() {
        Money money1 = new Money (new BigDecimal(25),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal(15),new Currency("Euros"));
        Money result = money1.subtract(money2);
        assertEquals(new BigDecimal("10.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testCorrectSubstractWithoutDecimalsWithNegativeResult() {
        Money money1 = new Money (new BigDecimal(15),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal(25),new Currency("Euros"));
        Money result = money1.subtract(money2);
        assertEquals(new BigDecimal("-10.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testCorrectSubstractWithDecimalsWithPositiveResult() {
        Money money1 = new Money (new BigDecimal("25.50"),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money result = money1.subtract(money2);
        assertEquals(new BigDecimal("10.18"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testCorrectSubstractWithDecimalsWithNegativeResult() {
        Money money1 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal("25.50"),new Currency("Euros"));
        Money result = money1.subtract(money2);
        assertEquals(new BigDecimal("-10.18"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSubstractWithDiferentCurrency() {
        Money money1 = new Money (new BigDecimal(15.00),new Currency("Euros"));
        Money money2 = new Money (new BigDecimal(25.00),new Currency("Eur"));
        Money result = money1.subtract(money2);
    }



    @Test
    public void testMultiplyWithoutDecimals() {
        Money money1 = new Money (new BigDecimal(15),new Currency("Euros"));
        Money result = money1.multiply(5);
        assertEquals(new BigDecimal("75.00"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }
    @Test
    public void testMultiplyWithDecimals() {
        Money money1 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money result = money1.multiply(5);
        assertEquals(new BigDecimal("76.60"), result.getQuantity());
        assertEquals("Euros", result.getCurrency().toString());
    }


    @Test
    public void testChangeWithoutDecimals() {
        Money money1 = new Money (new BigDecimal(15),new Currency("Euros"));
        Money result = money1.change(new BigDecimal(2), new Currency ("Dollars"));
        assertEquals(new BigDecimal("30.00"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }
    @Test
    public void testChangeWithDecimals() {
        Money money1 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money result = money1.change(new BigDecimal("3.14"), new Currency ("Dollars"));
        assertEquals(new BigDecimal("48.11"), result.getQuantity());
        assertEquals("Dollars", result.getCurrency().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeToSameCurrency() {
        Money money1 = new Money (new BigDecimal("15.32"),new Currency("Euros"));
        Money result = money1.change(new BigDecimal("3.14"), new Currency ("Euros"));
    }
}