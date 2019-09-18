package com.khairul.payment;

import com.khairul.payment.model.BillTransaction;
import com.khairul.payment.model.Customer;
import com.khairul.payment.model.CustomerType;
import com.khairul.payment.model.SaleType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class DiscountFactoryTest {

    @Test
    public void of_Given_BillTransaction_of_Employee_Return_30_Percent() {
        //Arrange
        LocalDate jointDate = LocalDate.now();
        Customer customer = new Customer(CustomerType.EMPLOYEE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(30, result.number().intValue());
        assertEquals(297, result.discounts().intValue());
    }

    @Test
    public void of_Given_BillTransaction_of_Affiliate_Store_Return_10_Percent() {
        //Arrange
        LocalDate jointDate = LocalDate.now();
        Customer customer = new Customer(CustomerType.AFFILIATE_STORE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(10, result.number().intValue());
    }

    @Test
    public void of_Given_BillTransaction_of_Loyalty_Customer_Return_5_Percent() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(2);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(5, result.number().intValue());
    }

    @Test
    public void of_Given_BillTransaction_with_Amount_$990_Return_$45() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        BigDecimal expectedDiscAmount = BigDecimal.valueOf(45);
        BigDecimal expectedDisc = expectedDiscAmount.divide(amount, BigDecimal.ROUND_HALF_EVEN)
                .multiply(BigDecimal.valueOf(100));

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(expectedDiscAmount, result.discounts());
        assertEquals(expectedDisc, result.number());
    }

    @Test
    public void of_Given_BillTransaction_with_Amount_$99_Return_$0() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(99);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(0, result.discounts().intValue());
        assertEquals(0, result.number().intValue());
    }

    @Test
    public void of_Given_BillTransaction_from_Grocery_Return_0_Percent() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.AFFILIATE_STORE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.GROCERY, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(0, result.number().intValue());
        assertEquals(0, result.discounts().intValue());
    }
}