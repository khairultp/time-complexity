package com.khairul.payment.impl;

import java.math.BigDecimal;

public class EmployeeDiscount extends BaseDiscount {

    public EmployeeDiscount(BigDecimal amount) {
        super(amount);
    }

    @Override
    public BigDecimal number() {
        return BigDecimal.valueOf(30);
    }
}
