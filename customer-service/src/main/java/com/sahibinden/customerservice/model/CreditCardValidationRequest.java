package com.sahibinden.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreditCardValidationRequest {
    private long customerId;
    private long creditCardNumber;
}
