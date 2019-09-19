package com.khairul.payment.impl;

import java.math.BigDecimal;

public class AmountDiscount extends BaseDiscount {

    private BigDecimal minAmount = BigDecimal.valueOf(100);
    private BigDecimal number;
    private BigDecimal baseAmtDisc = BigDecimal.valueOf(5);
    private BigDecimal amtDisc;

    public AmountDiscount(BigDecimal amount) {
        super(amount);
        init();
    }

    private void init() {
        if (amount.compareTo(minAmount) >= 0) {
            amtDisc = amount.divide(minAmount).setScale(0, BigDecimal.ROUND_DOWN).multiply(baseAmtDisc);
        } else {
            amtDisc = BigDecimal.ZERO;
        }

        number = amtDisc.divide(amount, BigDecimal.ROUND_HALF_EVEN).multiply(BigDecimal.valueOf(100));
    }

    @Override
    public BigDecimal saveAmount() {
        return amtDisc;
    }

    @Override
    public BigDecimal number() {
        return number;
    }
}
