package com.sahibinden.customerservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerPremiumUpgradeRequest {
    private long customerId;
    private String cardHolder;
    private long creditCardNumber;
    private int cvv;
}
