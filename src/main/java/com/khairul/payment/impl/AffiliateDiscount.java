package com.khairul.payment.impl;

import java.math.BigDecimal;

public class AffiliateDiscount extends BaseDiscount {

    public AffiliateDiscount(BigDecimal amount) {
        super(amount);
    }

    @Override
    public BigDecimal number() {
        return BigDecimal.valueOf(10);
    }
}
