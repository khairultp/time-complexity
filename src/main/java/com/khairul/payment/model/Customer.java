package com.khairul.payment.model;

import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;
import java.time.Period;

public class Customer {

    private CustomerType type;
    private LocalDate jointDate;

    public Customer(CustomerType type, LocalDate jointDate) {
        assertNotNull("type is required !", type);
        assertNotNull("jointDate is required !", jointDate);

        this.type = type;
        this.jointDate = jointDate;
    }

    public CustomerType getType() {
        return type;
    }

    public boolean isLoyaltyCustomer() {
        Period joinDuration = Period.between(jointDate, LocalDate.now());
        if (joinDuration.getYears() < 2) {
            return false;
        }
        return true;
    }
}
