package com.PDL.Sesame.service;

import com.PDL.Sesame.Exception.ResourceNotFoundException;
import com.PDL.Sesame.dao.NotificationDao;
import com.PDL.Sesame.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public List<Notification> consulterNotifications() {
        return notificationDao.findAllByOrderByCreationDateDesc();
    }

    @Override
    public void marquerCommeLu(Long notificationId) {
        Notification notification = notificationDao.findById(notificationId).orElseThrow(
                () -> new ResourceNotFoundException("Notification", "id", notificationId)
        );
        notification.setEstLu(true);
        notificationDao.save(notification);
    }
}
