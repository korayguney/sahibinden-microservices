package com.sahibinden.notificationservice.repository;

import com.sahibinden.notificationservice.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationEntityRepository extends JpaRepository<NotificationEntity, Integer> {
}