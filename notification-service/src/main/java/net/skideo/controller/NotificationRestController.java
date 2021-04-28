package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.NotificationDto;
import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import net.skideo.repository.NotificationRepository;
import net.skideo.repository.PlayerRepository;
import net.skideo.service.notification.NotificationService;
import net.skideo.service.player.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NotificationRestController {

    private final NotificationService notificationService;

    @PostMapping("/notification")
    public void addNotification(@Valid @RequestBody NotificationDto notification) throws MessagingException {
        notificationService.addNotification(notification.getNotificationType(),notification.getMessage(),notification.getIdUser());
    }

    @GetMapping("/notifications")
    public Page<NotificationInfoDto> getMyNotifications(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "50") int size) {
        return notificationService.getMyNotifications(page,size);
    }

}
