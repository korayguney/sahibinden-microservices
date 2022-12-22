package com.sahibinden.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-service",
        url = "${clients.notification.url}"
)
public interface NotificationClient {

    @PostMapping("/notifications")
    public void sendNotification(@RequestBody NotificationRequest request);
}
