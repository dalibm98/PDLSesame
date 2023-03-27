package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Notification;
import com.PDL.Sesame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long> {


    List<Notification> findAllByOrderByCreationDateDesc();
    //List<Notification> findByDestinataireAndEstLuOrderByCreationDateDesc(User destinataire, Boolean estLu);
}



