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
    public void of_Given_BillTransaction_of_Retail_Employee_$990_Return_30_Percent_And_Save_$297() {
        //Arrange
        LocalDate jointDate = LocalDate.now();
        Customer customer = new Customer(CustomerType.EMPLOYEE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(30, result.number().intValue());
        assertEquals(297, result.saveAmount().intValue());
    }

    @Test
    public void of_Given_BillTransaction_of_Retail_Affiliate_Store_$990_Return_10_Percent_And_Save_$99() {
        //Arrange
        LocalDate jointDate = LocalDate.now();
        Customer customer = new Customer(CustomerType.AFFILIATE_STORE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(10, result.number().intValue());
        assertEquals(99, result.saveAmount().intValue());
    }

    @Test
    public void of_Given_BillTransaction_of_Retail_Loyalty_Customer_$990_Return_5_Percent_And_Save_$49_50cent() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(2);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(5, result.number().intValue());
        assertEquals(BigDecimal.valueOf(49.5).setScale(2), result.saveAmount());
    }

    @Test
    public void of_Given_BillTransaction_of_Retail_$990_Return_4_point_5_Percent_And_Save_$45() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(BigDecimal.valueOf(4.5), result.number());
        assertEquals(45, result.saveAmount().intValue());
    }

    @Test
    public void of_Given_BillTransaction_of_Retail_$99_Return_0_Percent_And_Save_$0() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.NORMAL, jointDate);
        BigDecimal amount = BigDecimal.valueOf(99);
        BillTransaction bill = BillTransaction.of(customer, SaleType.RETAIL, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(BigDecimal.ZERO, result.saveAmount());
        assertEquals(BigDecimal.ZERO.setScale(1), result.number());
    }

    @Test
    public void of_Given_BillTransaction_of_Grocery_Return_0_Percent_And_Save_$0() {
        //Arrange
        LocalDate jointDate = LocalDate.now().minusYears(1);
        Customer customer = new Customer(CustomerType.AFFILIATE_STORE, jointDate);
        BigDecimal amount = BigDecimal.valueOf(990);
        BillTransaction bill = BillTransaction.of(customer, SaleType.GROCERY, amount);

        //Act
        DiscountService result = DiscountFactory.of(bill);

        //Assert
        assertEquals(BigDecimal.ZERO, result.number());
        assertEquals(BigDecimal.ZERO, result.saveAmount());
    }
}