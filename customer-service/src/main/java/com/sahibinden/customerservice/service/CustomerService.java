package com.sahibinden.customerservice.service;

import com.sahibinden.customerservice.model.*;
import com.sahibinden.customerservice.repository.CustomerRepository;
import com.turkcell.customerservice.exception.CustomerDoesNotExistException;
import com.turkcell.customerservice.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;

    public long registerCustomer(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .build();
        customerEntity = repository.save(customerEntity);
        return customerEntity.getId();
    }

    public boolean registerCustomerAsPremium(CustomerPremiumUpgradeRequest request) {
        // check customer data from db
        CustomerEntity customerEntity = repository.findById(request.getCustomerId())
                .orElseThrow(CustomerDoesNotExistException::new);

        // map to CreditCardValidationRequest
        CreditCardValidationRequest validationRequest = CreditCardValidationRequest.builder()
                .customerId(request.getCustomerId())
                .creditCardNumber(request.getCreditCardNumber())
                .build();

        // validate credit card
        CreditCardValidationResponse response = restTemplate.postForObject("http://localhost:8081/creditcards/validate",
                validationRequest, CreditCardValidationResponse.class);

        if(response.isValid()) {
            // retrieve payment
            log.info("Payment retrieved successfully!");

            // update customer data as premium
            customerEntity.setPremium(Boolean.TRUE);
            repository.save(customerEntity);
        }
        // log result
        if(response.isValid()) {
            log.info("Customer : {} 's membership upgraded to premium! " , customerEntity.getFirstname());
        } else {
            log.info("Customer with ID : {} NOT upgraded to premium! " , request.getCustomerId());
        }

        // return result
        return response.isValid();
    }
}
