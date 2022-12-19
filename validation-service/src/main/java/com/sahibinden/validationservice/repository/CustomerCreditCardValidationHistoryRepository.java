package com.sahibinden.validationservice.repository;

import com.sahibinden.validationservice.model.CustomerCreditCardValidationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreditCardValidationHistoryRepository extends JpaRepository<CustomerCreditCardValidationHistory, Long> {
}