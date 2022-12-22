package com.sahibinden.customerservice.service;

import com.sahibinden.customerservice.model.*;
import com.sahibinden.customerservice.repository.CustomerRepository;
import com.sahibinden.customerservice.exception.CustomerDoesNotExistException;
import com.sahibinden.notification.NotificationClient;
import com.sahibinden.notification.NotificationRequest;
import com.sahibinden.notification.NotificationResponse;
import com.sahibinden.validation.CreditCardValidationClient;
import com.sahibinden.validation.CreditCardValidationRequest;
import com.sahibinden.validation.CreditCardValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final RestTemplate restTemplate;
    private final CreditCardValidationClient cardValidationClient;
    private final NotificationClient notificationClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

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
        //CreditCardValidationResponse response = restTemplate.postForObject("http://VALIDATION-SERVICE/creditcards/validate",
        //        validationRequest, CreditCardValidationResponse.class);
        CreditCardValidationResponse response = cardValidationClient.validateCreditCard(validationRequest);

        if (response.isValid()) {
            // retrieve payment
            log.info("Payment retrieved successfully!");

            // update customer data as premium
            customerEntity.setPremium(Boolean.TRUE);
            repository.save(customerEntity);

            // send notification
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuit-breaker");

            NotificationRequest notificationRequest = NotificationRequest.builder()
                    .toCustomerId(customerEntity.getId())
                    .toCustomerEmail(customerEntity.getEmail())
                    .message("Hi " + customerEntity.getFirstname() + ", your membership is upgraded to premium!")
                    .build();

            NotificationResponse notificationResponse =
                    circuitBreaker.run(() -> restTemplate.postForObject("http://NOTIFICATION-SERVICE/notifications",
                    notificationRequest, NotificationResponse.class),
                            throwable -> getDefaultNotificationResponse());

            log.info("Result of notify : " + notificationResponse);

            // notificationClient.sendNotification(
            //         NotificationRequest.builder()
            //                 .toCustomerId(customerEntity.getId())
            //                 .toCustomerEmail(customerEntity.getEmail())
            //                 .message("Hi " + customerEntity.getFirstname() + ", your membership is upgraded to premium!")
            //                 .build()
            // );
        }

        // log result
        if (response.isValid()) {
            log.info("Customer : {} 's membership upgraded to premium! ", customerEntity.getFirstname());
        } else {
            log.info("Customer with ID : {} NOT upgraded to premium! ", request.getCustomerId());
        }

        // return result
        return response.isValid();
    }

    private NotificationResponse getDefaultNotificationResponse() {
        return NotificationResponse.builder()
                .isSuccess(false)
                .details("An error occured -- Default response!")
                .build();
    }
}
