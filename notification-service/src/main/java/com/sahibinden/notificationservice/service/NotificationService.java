package com.sahibinden.notificationservice.service;

import com.sahibinden.notification.NotificationRequest;
import com.sahibinden.notificationservice.model.NotificationEntity;
import com.sahibinden.notificationservice.repository.NotificationEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationEntityRepository repository;

    public void sendNotification(NotificationRequest request) {
        repository.save(
                NotificationEntity.builder()
                        .toCustomerId(request.getToCustomerId())
                        .toCustomerEmail(request.getToCustomerEmail())
                        .message(request.getMessage())
                        .sender("sahibinden")
                        .sentAt(LocalDateTime.now())
                        .build()
        );
        log.info("Notification sent :   {}" , request.getMessage());
    }
}
