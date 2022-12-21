package com.sahibinden.notificationservice.rabbitmq;

import com.sahibinden.notification.NotificationRequest;
import com.sahibinden.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request) {
        service.sendNotification(request);
    }

}
