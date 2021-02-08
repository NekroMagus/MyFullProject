package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.NotificationDto;
import net.skideo.service.notification.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationRestController {

    private final NotificationService notificationService;

    @PostMapping
    public void addNotification(@Valid @RequestBody NotificationDto notification) {
        notificationService.addNotification(notification.getNotificationEnum(),notification.getIdUser());
    }
}
