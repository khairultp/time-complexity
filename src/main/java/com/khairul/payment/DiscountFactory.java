package com.khairul.payment;

import com.khairul.payment.impl.*;
import com.khairul.payment.model.CustomerType;
import com.khairul.payment.model.SaleType;
import com.khairul.payment.model.BillTransaction;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

public class DiscountFactory {

    private DiscountFactory(){}

    public static DiscountService of(BillTransaction bill) {
        assertNotNull("bill is required !", bill);

        BigDecimal amount = bill.getAmount();

        if (bill.getType() == SaleType.GROCERY) {
            return new GroceryDiscount(amount);
        } else if (bill.getCustomer().getType() == CustomerType.EMPLOYEE) {
            return new EmployeeDiscount(amount);
        } else if (bill.getCustomer().getType() == CustomerType.AFFILIATE_STORE) {
            return new AffiliateDiscount(amount);
        } else if (bill.getCustomer().isLoyaltyCustomer()) {
            return new LoyaltyCustomerDiscount(amount);
        }
        return new AmountDiscount(amount);
    }

}
