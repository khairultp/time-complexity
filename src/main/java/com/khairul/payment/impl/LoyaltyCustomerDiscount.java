package com.khairul.payment.impl;

import java.math.BigDecimal;

public class LoyaltyCustomerDiscount extends BaseDiscount {

    public LoyaltyCustomerDiscount(BigDecimal amount) {
        super(amount);
    }

    @Override
    public BigDecimal number() {
        return BigDecimal.valueOf(5);
    }
}
