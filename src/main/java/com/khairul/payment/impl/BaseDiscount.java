package com.khairul.payment.impl;

import com.khairul.payment.DiscountService;

import java.math.BigDecimal;

public abstract class BaseDiscount implements DiscountService {

    protected final BigDecimal amount;

    public BaseDiscount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal saveAmount() {
        return amount.multiply(number().divide(BigDecimal.valueOf(100)));
    }
}
