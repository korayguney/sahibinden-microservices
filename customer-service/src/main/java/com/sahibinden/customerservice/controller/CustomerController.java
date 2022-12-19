package com.sahibinden.customerservice.controller;

import com.sahibinden.customerservice.service.CustomerService;
import com.sahibinden.customerservice.model.Customer;
import com.sahibinden.customerservice.model.CustomerPremiumUpgradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/customers")
    public long registerCustomer(@RequestBody Customer customer) {
        return service.registerCustomer(customer);
    }

    @PostMapping("/customers/premium")
    public boolean registerCustomerAsPremium(@RequestBody CustomerPremiumUpgradeRequest request) {
        return service.registerCustomerAsPremium(request);
    }

}
