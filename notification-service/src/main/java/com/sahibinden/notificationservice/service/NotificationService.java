package com.sahibinden.notificationservice.service;

import com.sahibinden.notification.NotificationRequest;
import com.sahibinden.notification.NotificationResponse;
import com.sahibinden.notificationservice.model.NotificationEntity;
import com.sahibinden.notificationservice.repository.NotificationEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationEntityRepository repository;

    public NotificationResponse sendNotification(NotificationRequest request) {
        NotificationEntity entity = repository.save(
                NotificationEntity.builder()
                        .toCustomerId(request.getToCustomerId())
                        .toCustomerEmail(request.getToCustomerEmail())
                        .message(request.getMessage())
                        .sender("sahibinden")
                        .sentAt(LocalDateTime.now())
                        .build()
        );
        log.info("Notification sent :   {}", request.getMessage());
        if (Objects.nonNull(entity)) {
            return NotificationResponse.builder()
                    .isSuccess(true)
                    .details(request.getMessage())
                    .build();
        } else {
            return NotificationResponse.builder()
                    .isSuccess(false)
                    .details("An error occured during notification sent!")
                    .build();
        }
    }
}
