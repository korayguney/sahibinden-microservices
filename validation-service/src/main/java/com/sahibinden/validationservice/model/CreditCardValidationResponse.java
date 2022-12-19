package com.sahibinden.validationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardValidationResponse {
    private boolean isValid;
}
