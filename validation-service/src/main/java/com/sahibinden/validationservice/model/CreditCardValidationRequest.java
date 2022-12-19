package com.sahibinden.validationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardValidationRequest {
    private long customerId;
    private long creditCardNumber;
}
