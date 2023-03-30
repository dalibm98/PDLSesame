package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.Notification;
import com.PDL.Sesame.service.NotificationService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

  //  @ApiOperation(value = "Consulter toutes les notifications")
    @GetMapping("")
    public List<Notification> consulterNotifications() {
        return notificationService.consulterNotifications();
    }

   // @ApiOperation(value = "Marquer une notification comme lue")
    @PostMapping("/{notificationId}/lire")
    public void marquerCommeLu(/*@ApiParam(value = "ID de la notification à marquer comme lue", required = true */@PathVariable Long notificationId) {
        notificationService.marquerCommeLu(notificationId);
    }
}
