package com.sahibinden.notificationservice.controller;

import com.sahibinden.notification.NotificationRequest;
import com.sahibinden.notification.NotificationResponse;
import com.sahibinden.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/notifications")
    public NotificationResponse sendNotification(@RequestBody NotificationRequest request){
        return service.sendNotification(request);
    }

}
