package com.sahibinden.notificationservice;

import com.sahibinden.notificationservice.config.NotificationConfig;
import com.sahibinden.rabbitmq.RabbitMQMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.sahibinden.rabbitmq",
                "com.sahibinden.notificationservice"
        }
)
@EnableEurekaClient
public class NotificationServiceApplication {//implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

   //@Autowired
   //RabbitMQMessageProducer producer;

   //@Autowired
   //NotificationConfig config;

    //@Override
    //public void run(String... args) throws Exception {
    //    producer.publish(new Customer("Ali Veli", 35), config.getNotificationExchange(), config.getNotificationRoutingKey());
    //}
//
    //record Customer(String name, int age) {}
}
