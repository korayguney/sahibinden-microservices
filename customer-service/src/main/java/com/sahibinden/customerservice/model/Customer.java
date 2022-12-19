package com.sahibinden.customerservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private String firstname;
    private String lastname;
    private String email;
}
