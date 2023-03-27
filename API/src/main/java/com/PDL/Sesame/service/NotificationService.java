package com.PDL.Sesame.service;

import com.PDL.Sesame.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> consulterNotifications();
    void marquerCommeLu(Long notificationId);
}
