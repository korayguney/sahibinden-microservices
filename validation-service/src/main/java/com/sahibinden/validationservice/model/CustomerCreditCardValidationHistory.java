package com.sahibinden.validationservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "validationhistory")
public class CustomerCreditCardValidationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private long customerId;
    private boolean isValid;
    private int binNumber;
    private LocalDateTime validationDate;
}
