package com.sahibinden.validation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("validation-service")
public interface CreditCardValidationClient {

    @PostMapping("/creditcards/validate")
    public CreditCardValidationResponse validateCreditCard(@RequestBody CreditCardValidationRequest request);


}
