package com.khairul.payment.model;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

public class BillTransaction {

    private final Customer customer;
    private final SaleType type;
    private final BigDecimal amount;

    private BillTransaction(Customer customer, SaleType type, BigDecimal amount) {
        assertNotNull("customer is required !", customer);
        assertNotNull("type is required !", type);
        assertNotNull("amount is required !", amount);

        this.customer = customer;
        this.type = type;
        this.amount = amount;
    }

    public static BillTransaction of(Customer customer, SaleType type, BigDecimal amount) {
        return new BillTransaction(customer, type, amount);
    }

    public Customer getCustomer() {
        return customer;
    }

    public SaleType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
