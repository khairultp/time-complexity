package com.khairul.payment.impl;

import java.math.BigDecimal;

public class GroceryDiscount extends BaseDiscount {

    public GroceryDiscount(BigDecimal amount) {
        super(amount);
    }

    @Override
    public BigDecimal number() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal discounts() {
        return BigDecimal.ZERO;
    }
}
